package com.catan.service;

import com.catan.exceptions.PasswordIncorrectException;
import com.catan.exceptions.UserAlreadyExistsException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.User;
import com.catan.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlayerService userService;

    @Autowired
    public UserService(UserRepository userRepository,
                       PlayerService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public User getUserByName(String username) {
        Optional<User> userDB = userRepository.findByUsername(username);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        }else{
            return userDB.get();
        }
    }

    public String hashPassword(String password){
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public List<User> getALlUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    public User getUserById(int id) {
        Optional<User> userDB = userRepository.findById(id);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            return userDB.get();
        }
    }

    public User logUserIn(String username, String password){
        User userDB = this.getUserByName(username);
        if(hashPassword(password).equals(userDB.getPassword())){
            return userDB;
        } else{
            throw new PasswordIncorrectException("Password is incorrect");
        }
    }

    public User registerUser(String username, String password){
        if(this.userRepository.findByUsername(username).isEmpty()){
            User newUser = new User(username, hashPassword(password));
            return this.userRepository.save(newUser);
        }else{
            throw new UserAlreadyExistsException("Cannot registry a user that is already in the database");
        }
    }

    public User deleteUserById(int id){
        Optional<User> userDB = userRepository.findById(id);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            userRepository.deleteById(id);
            return userDB.get();
        }
    }

    public User updateUserById(int id, User newUser){
        Optional<User> oldUserDB = userRepository.findById(id);
        if(oldUserDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            User updatedUser = oldUserDB.get();
            updatedUser.setUsername(newUser.getUsername());
            updatedUser.setPassword(hashPassword(newUser.getPassword()));
            updatedUser.setRankingPoints(newUser.getRankingPoints());
            return userRepository.save(updatedUser);
        }
    }

}
