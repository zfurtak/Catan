package com.catan.api;

import com.catan.model.User;
import com.catan.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v3/api-docs")
public class UsersApiController implements UsersApi {
    private UserService userService;

    @Override
    public User createUser(User user) {
    return userService.registerUser(user.getUsername(), user.getPassword());
    }
//
//    @Override
//    public ResponseEntity<UserDto> addPermissions(UpdateUserPermissionsDto updateUserPermissionsDto) {
//        return userService.addPermissions(updateUserPermissionsDto).map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException("User does not exists!"));
//    }

}