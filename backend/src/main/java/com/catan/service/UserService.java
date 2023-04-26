package com.catan.service;

import com.catan.exceptions.PasswordIncorrectException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Player;
import com.catan.model.User;
import com.catan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByName(String username) {
        Optional<User> userDB = userRepository.findByUsername(username);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("Player not found");
        }else{
            return userDB.get();
        }
    }

    public User getUserById(int id) {
        Optional<User> userDB = userRepository.findById(id);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("Player not found");
        }else{
            return userDB.get();
        }
    }

    public User logUserIn(String username, String password){
        User userDB = this.getUserByName(username);
        if(password.equals(userDB.getPassword())){
            return userDB;
        }else{
            throw new PasswordIncorrectException("Password is incorrect");
        }
    }

    public User updateRankingPoints(Player player, int rankingPoints){
        User user = player.getUser();
        user.setRankingPoints(rankingPoints);
        return this.userRepository.save(user);
    }
}
