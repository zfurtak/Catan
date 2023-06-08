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
 * Service for the User class.
 * @author Zuzanna Furtak
 * @author Agnieszka Lasek
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Initialize the service.
     * @param userRepository the User repository associated to this service
     */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Returns the user with the specified username.
     * @exception UserNotFoundException if the user with the specified username is not saved in the repository
     * @param username name of the user to be returned
     * @return user with the specified username
     * @author Zuzanna Furtak
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
     * Hashes the provided password.
     * @param password the password to be hashed
     * @return hashed password
     * @author Agnieszka Lasek
     */
    public String hashPassword(String password){
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    /**
     * Returns all the users saved in the user repository.
     * @return list of all users in repository
     */
    public List<User> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    /**
     * Returns user with the specified id.
     * @exception UserNotFoundException if the user with the specified id is not saved in the repository
     * @param id id from the user to be returned
     * @return user with the specified id
     * @author Zuzanna Furtak
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
     * Returns user with the specified username if the user is found and the specified password is the one associated to this user.
     * @exception UserNotFoundException if the user with the specified username is not saved in the repository
     * @exception PasswordIncorrectException if the specified password does not match the password saved for the user with the specified username
     * @param username username of the user to be returned
     * @param password password of the user to be returned
     * @return user with the specified username and password
     * @author Zuzanna Furtak
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
     * Returns the new user created with the specified username and the specified password.
     * @exception UsernameTooShortException if the specified username has less than four characters
     * @exception UserAlreadyExistsException if there is already a user with the specified username saved in the repository
     * @param username username of the user to be created
     * @param password password of the user to be created
     * @return new user with the specified username and password
     * @author Zuzanna Furtak
     */
    public User registerUser(String username, String password){
        if(username.length() >= 4){
            if(this.userRepository.findByUsername(username).isEmpty()){
                User newUser = new User(username, hashPassword(password));
                return this.userRepository.save(newUser);
            }else{
                throw new UserAlreadyExistsException("Cannot register a user that is already in the database");
            }
        } else{
            throw new UsernameTooShortException("Usarname provided is too short, it has less than 4 characters");
        }

    }

    /**
     * Deletes the user with the specified id from the repository. It returns null if the user was deleted successfully.
     * @exception UserNotFoundException if the user with the specified id is not saved in the repository
     * @param id id of the user to be deleted
     * @return null if deletion is successful
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
     * Updates the information of the user with the specified id. The new information is provided by the newUser parameter.
     * @exception UserNotFoundException if the user with the specified id is not saved in the repository
     * @param id id of the user to be updated
     * @param newUser user containing the new information to be stored in the user
     * @return the new user after associating it to the specified id
     * @author Agnieszka Lasek
     */
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
