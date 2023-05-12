package com.catan.api;

import com.catan.exceptions.ErrorDto;
import com.catan.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "USER", description = "USER")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")})
public interface UsersApi {
    @Operation(summary = "", description = "Creates user. Please note, that permittedProjects value should be equal READ or WRITE")
    @PostMapping(value = "/users/addUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    User createUser(@RequestBody @Valid User user);
//
//    @ApiOperation(value = "", notes = "Adds permissions for user")
//    @PutMapping(value = "/user/permissions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<UserDto> addPermissions(@RequestBody @Valid UpdateUserPermissionsDto updateUserPermissionsDto);
}