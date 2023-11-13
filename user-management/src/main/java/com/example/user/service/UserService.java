package com.example.user.service;

import com.example.user.dto.UserLoginRequest;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.model.Role;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String signUp(UserRequest userRequest) {
        User user1 = userRepository.findByUsername(userRequest.getUsername());
        if(user1==null){
            mapAndSave(userRequest);
            return "Signed up successfully!";
        }
        else if(Objects.equals(user1.getUsername(), userRequest.getUsername())){
            return "User is already there! Please logIn!";
        }
        else {
            return "Invalid!";
        }
    }

    public void mapAndSave(UserRequest userRequest){
        UUID id = UUID.randomUUID();
        Set <Role> roles = new HashSet<Role>();
        roles.add(new Role("1","Customer"));
        User user = new User(id.toString(),
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPhoneNumber(),
                userRequest.getUsername(),
                passwordEncoder.encode(userRequest.getPassword()),
                roles,
                userRequest.getAddress());

        userRepository.save(user);
    }

    public boolean signIn(UserLoginRequest userLoginRequest) {
            User user = userRepository.findByUsername(userLoginRequest.getUsername());
        if (user!=null) {
            return Objects.equals(user.getUsername(), userLoginRequest.getUsername()) && passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword());
        }else {
            return false;
        }

    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        return mapToUserResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    public User updateUser(UserRequest userRequest) {

        User user = userRepository.findById(userRequest.getId()).orElse(null);
        if (user != null) {
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setRoles(userRequest.getRoles());
            user.setAddress(userRequest.getAddress());
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUsername(),
                user.getRoles(),
                user.getAddress()
        );
    }


}
