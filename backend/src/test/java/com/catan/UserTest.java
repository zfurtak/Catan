package com.catan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.catan.exceptions.PasswordIncorrectException;
import com.catan.exceptions.UserAlreadyExistsException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.exceptions.UsernameTooShortException;

import com.catan.model.User;
import com.catan.repository.UserRepository;
import com.catan.service.UserService;

/**
 * Tests for User and UserService.
 * @author Minerva Gomez
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private UserService service;

    private User user;
    private User name;

    @BeforeEach
    void deleteBefore(){
        repositoryUser.deleteAll();
    }

    @AfterEach
    void deleteAfter(){
        repositoryUser.deleteAll();
    }

    //register a new user in the database, get said user by its name and check that it returns the correct user
    @Test
    void newUserName() {
        user = service.registerUser("user", "pass");
        assertEquals("user", user.getUsername());
    }

    //register a new user in the database, get said user by its name and check that its password is the string transformed by the hashPassword method
    @Test
    void newUserPassword(){
        user = service.registerUser("user", "pass");
        assertEquals(service.hashPassword("pass"), user.getPassword());
    }

    //register a new user in the database, get said user by its name and check that its ranking points are 0
    @Test
    void newUserRanking(){
        user = service.registerUser("user", "pass");
        assertEquals(0, user.getRankingPoints());
    }

    //register a new user in the database, trying to create a new user with the same username will throw UserAlreadyExistsException
    @Test
    void newUserAlreadyExistsException() {
        service.registerUser("user", "pass");
        assertThrows(UserAlreadyExistsException.class, () -> service.registerUser("user", "word"));
    }

    //registering a user with an username with less than four characters throws UsernameTooShortException
    @Test
    void UsernameIsTooShortException(){
        assertThrows(UsernameTooShortException.class, () -> service.registerUser("usr", "word"));
    }

    //register two new users in the database, check that their ids are different
    @Test
    void newUsersIdGeneration(){
        user = service.registerUser("user", "pass");
        name = service.registerUser("name", "word");
        assertNotEquals(user.getId(), name.getId());
    }

    //get a user with getUserByName from an empty database throws UserNotFoundException
    @Test
    void getUsersByNameFromEmpty(){
        assertThrows(UserNotFoundException.class, () -> service.getUserByName("user"));
    }

    //register a new user, get its id, use it to get the user with getUserById and check if it returns the correct user
    @Test
    void getUsersById(){
        user = service.registerUser("user", "pass");
        int id = user.getId();
        user = service.getUserById(id);
        assertEquals("user", user.getUsername());
    }

    //get a user with getUserById from an empty database throws UserNotFoundException
    @Test
    void getUsersByIdException(){
        assertThrows(UserNotFoundException.class, () -> service.getUserById(0));
    }

    //register a new user, store in logUser the user returned by the logUserIn and check if they are the same
    @Test
    void logUser(){
        user = service.registerUser("user", "pass");
        name = service.logUserIn("user", "pass"); 
        assertEquals(user.getId(), name.getId());
    }

    //using logUserIn with a wrong password throws PasswordIncorrectException
    @Test
    void logUserUsernameException(){
        assertThrows(UserNotFoundException.class, () -> service.logUserIn("user", "word"));
    }

    //using logUserIn with a wrong password throws PasswordIncorrectException
    @Test
    void logUserPasswordException(){
        user = service.registerUser("user", "pass");
        assertThrows(PasswordIncorrectException.class, () -> service.logUserIn("user", "word"));
    }

    //register a new user, get the user id, delete the user using its id and check that a user with that id does not exist
    @Test
    void deleteUserCheckingId(){
        user = service.registerUser("user", "pass");
        int id = user.getId();
        service.deleteUserById(id);
        assertThrows(UserNotFoundException.class, () -> service.getUserById(id));
    }

    //register a new user, get the user id, delete the user using its id and check that a user with the username we used to register does not exist 
    @Test
    void deleteUserCheckingUsername(){
        user = service.registerUser("user", "pass");
        int id = user.getId();
        service.deleteUserById(id);
        assertThrows(UserNotFoundException.class, () -> service.getUserByName("user"));
    }

    //using deleteUserById when that id's user does not exist throws UserNotFoundException
    @Test
    void deleteUserException(){
        assertThrows(UserNotFoundException.class, () -> service.deleteUserById(0));
    }

    //register a new user, get its id, update user with their id, get the user by id and check its username has been changed
    @Test
    void updateUserUsername(){
        user = service.registerUser("user", "pass");
        int id = user.getId();
        name = new User("name", "word");
        user = service.updateUserById(id, name);
        assertEquals(name.getUsername(), user.getUsername());
    }

    //register a new user, get its id, update user with their id, get the user by id and check its password has been changed
    @Test
    void updateUserPassword(){
        user = service.registerUser("user", "pass");
        int id = user.getId();
        name = new User("name", "word");
        user = service.updateUserById(id, name);
        assertEquals(service.hashPassword(name.getPassword()), user.getPassword());
    }

    //register a new user, get its id, update user with their id, get the user by id and check its ranking points have been changed
    @Test
    void updateUserRanking(){
        user = service.registerUser("user", "pass");
        user.setRankingPoints(5);
        int id = user.getId();
        name = new User("name", "word");
        name = service.updateUserById(id, name);
        assertNotEquals(name.getRankingPoints(), user.getRankingPoints());
    }

    //register two new users, get all users and check the size of the list is two
    @Test
    void getAllUsersInRepository(){
        service.registerUser("user", "pass");
        service.registerUser("name", "word");
        List<User> users = service.getAllUsers();
        assertTrue(users.size() == 2);
    }

}
