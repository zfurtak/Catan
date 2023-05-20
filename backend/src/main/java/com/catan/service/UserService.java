package com.catan.service;

import com.catan.exceptions.PasswordIncorrectException;
import com.catan.exceptions.UserAlreadyExistsException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.exceptions.UsernameTooShortException;
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

/**
 * Service for the User class
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlayerService userService;

    /**
     * initialize the service
     * @param userRepository the user repository associated to this service
     * @param userService 
     */
    @Autowired
    public UserService(UserRepository userRepository,
                       PlayerService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * returns the user with the specified username
     * @exception UserNotFoundException if the user with the specified username is not saved in the repository
     * @param username name of the user
     * @return user with the specified username
     */
    public User getUserByName(String username) {
        Optional<User> userDB = userRepository.findByUsername(username);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        }else{
            return userDB.get();
        }
    }

    /**
     * Hashes the provided password
     * @param password
     * @return hashed password
     */
    public String hashPassword(String password){
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    /**
     * returns all the users saved in the user repository
     * @return list of all users in repository
     */
    public List<User> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    /**
     * @exception UserNotFoundException if the user with the specified id is not saved in the repository
     * @param id
     * @return user with the specified id
     */
    public User getUserById(int id) {
        Optional<User> userDB = userRepository.findById(id);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            return userDB.get();
        }
    }

    /**
     * @exception UserNotFoundException if the user with the specified username is not saved in the repository
     * @exception PasswordIncorrectException if the specified password does not match the password saved for the user with the specified username
     * @param username
     * @param password
     * @return user with the specified username and password
     */
    public User logUserIn(String username, String password){
        User userDB = this.getUserByName(username);
        if(hashPassword(password).equals(userDB.getPassword())){
            return userDB;
        } else {
            throw new PasswordIncorrectException("Password is incorrect");
        }
    }
    /**
     * @exception UsernameTooShortException if the specified username has less than four characters
     * @exception UserAlreadyExistsException if there is already a user with the specified username
     * @param username
     * @param password
     * @return new user with the specified username and password
     */
    public User registerUser(String username, String password){
        if(username.length() >= 4){
            if(this.userRepository.findByUsername(username).isEmpty()){
                User newUser = new User(username, hashPassword(password));
                return this.userRepository.save(newUser);
            }else{
                throw new UserAlreadyExistsException("Cannot registry a user that is already in the database");
            }
        } else{
            throw new UsernameTooShortException("Usarname provided is too short, it has less than 4 characters");
        }

    }

    /**
     * @exception UserNotFoundException if the user with the specified id is not saved in the repository
     * @param id
     * @return null
     */
    public User deleteUserById(int id){
        Optional<User> userDB = userRepository.findById(id);
        if(userDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            userRepository.deleteById(id);
            return userDB.get();
        }
    }

    /**
     * @exception UserNotFoundException if the user with the specified id is not saved in the repository
     * @param id
     * @param newUser
     * @return the new user after associating it to the specified id
     */
    public User updateUserById(int id, User newUser){
        Optional<User> oldUserDB = userRepository.findById(id);
        if(oldUserDB.isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            User updatedUser = oldUserDB.get();
            // TODO: check if we want to be able to change username/password
            updatedUser.setUsername(newUser.getUsername());
            updatedUser.setPassword(hashPassword(newUser.getPassword()));
            updatedUser.setRankingPoints(newUser.getRankingPoints());
            return userRepository.save(updatedUser);
        }
    }

}
