package com.catan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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


@SpringBootTest
class UserTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService control;

    private User user;
    private User name;

    @BeforeEach
    void deleteBefore(){
        repository.deleteAll();
    }

    @AfterEach
    void deleteAfter(){
        repository.deleteAll();
    }

    //register a new user in the database, get said user by its name and check that it returns the correct user
    @Test
    void newUserName() {
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        assertEquals("user", user.getUsername());
    }

    //register a new user in the database, get said user by its name and check that its password is the string transformed by the hashPassword method
    @Test
    void newUserPassword(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        assertEquals(control.hashPassword("pass"), user.getPassword());
    }

    //register a new user in the database, get said user by its name and check that its ranking points are 0
    @Test
    void newUserRanking(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        assertEquals(0, user.getRankingPoints());
    }

    ////register a new user in the database, trying to create a new user with the same username will throw UserAlreadyExistsException
    @Test
    void newUserAlreadyExistsException() {
        control.registerUser("user", "pass");
        assertThrows(UserAlreadyExistsException.class, () -> control.registerUser("user", "word"));
    }

    @Test
    void UsernameIsTooShortException(){
        assertThrows(UsernameTooShortException.class, () -> control.registerUser("usr", "word"));
    }

    //register two new users in the database, check that their ids are different
    @Test
    void newUsersIdGeneration(){
        control.registerUser("user", "pass");
        control.registerUser("name", "word");
        user = control.getUserByName("user");
        name = control.getUserByName("name");
        assertNotEquals(user.getId(), name.getId());
    }

    //get a user with getUserByName from an empty database throws UserNotFoundException
    @Test
    void getUsersByNameFromEmpty(){
        assertThrows(UserNotFoundException.class, () -> control.getUserByName("user"));
    }

    //register a new user, get its id, use it to get the user with getUserById and check if it returns the correct user
    @Test
    void getUsersById(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        int id = user.getId();
        user = control.getUserById(id);
        assertEquals("user", user.getUsername());
    }

    //get a user with getUserById from an empty database throws UserNotFoundException
    @Test
    void getUsersByIdException(){
        assertThrows(UserNotFoundException.class, () -> control.getUserById(0));
    }

    //register a new user, store in logUser the user returned by the logUserIn and check if they are the same
    @Test
    void logUser(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        User logUser = control.logUserIn("user", "pass"); 
        assertEquals(user.getId(), logUser.getId());
    }


    //using logUserIn with a wrong password throws PasswordIncorrectException
    @Test
    void logUserException(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        assertThrows(PasswordIncorrectException.class, () -> control.logUserIn("user", "word"));
    }

    //register a new user, get the user id, delete the user using its id and check that a user with that id does not exist
    @Test
    void deleteUserCheckingId(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        int id = user.getId();
        control.deleteUserById(id);
        assertThrows(UserNotFoundException.class, () -> control.getUserById(id));
    }

    //register a new user, get the user id, delete the user using its id and check that a user with the username we used to register does not exist 
    @Test
    void deleteUserCheckingUsername(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        int id = user.getId();
        control.deleteUserById(id);
        assertThrows(UserNotFoundException.class, () -> control.getUserByName("user"));
    }

    //using deleteUserById when that id's user does not exist throws UserNotFoundException
    @Test
    void deleteUserException(){
        assertThrows(UserNotFoundException.class, () -> control.deleteUserById(0));
    }

    //register a new user, get its id, update user with their id, get the user by id and check its username has been changed
    @Test
    void updateUserUsername(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        int id = user.getId();
        name = new User("name", "word");
        control.updateUserById(id, name);
        user = control.getUserById(id);
        assertEquals(name.getUsername(), user.getUsername());
    }

    //register a new user, get its id, update user with their id, get the user by id and check its password has been changed
    @Test
    void updateUserPassword(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        int id = user.getId();
        name = new User("name", "word");
        control.updateUserById(id, name);
        user = control.getUserById(id);
        assertEquals(control.hashPassword(name.getPassword()), user.getPassword());
    }

    //register a new user, get its id, update user with their id, get the user by id and check its ranking points have been changed
    @Test
    void updateUserRanking(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        int id = user.getId();
        name = new User("name", "word");
        control.updateUserById(id, name);
        user = control.getUserById(id);
        assertEquals(name.getRankingPoints(), user.getRankingPoints());
    }

}
