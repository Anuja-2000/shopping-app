package com.example.user.controller;

import com.example.user.dto.UserLoginRequest;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserRequest userRequest) {
        userService.signUp(userRequest);
    }

    @GetMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody UserLoginRequest userLoginRequest) {
        boolean isSigned = userService.signIn(userLoginRequest);
        return isSigned? "Logged in Successfully!" :"Authentication failed";
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserResponse> getUserById(@RequestParam String id) {
        UserResponse userResponse = userService.getUserById(id);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@RequestBody UserRequest userRequest) {
        return userService.updateUser(userRequest);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean deleteUser(@PathVariable(value = "id") String id) {
        return userService.deleteUser(id);
    }
}
