package com.jm.SpringBoot.SpringBoot.rest;

import com.jm.SpringBoot.SpringBoot.dto.RoleDTO;
import com.jm.SpringBoot.SpringBoot.dto.UserDTO;
import com.jm.SpringBoot.SpringBoot.mappers.RoleMapper;
import com.jm.SpringBoot.SpringBoot.mappers.UserMapper;
import com.jm.SpringBoot.SpringBoot.model.Role;
import com.jm.SpringBoot.SpringBoot.model.User;
import com.jm.SpringBoot.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/users/")
public class AdminRestControllerV1 {
    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.show(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(user), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.userService.save(user);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    public ResponseEntity<User> updateUser(@RequestBody User user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.userService.save(user);

        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = this.userService.show(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/roles/")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<Role> roles = this.userService.getRoles();
        List<RoleDTO> roleDTOs = roles.stream().map(RoleMapper.INSTANCE::toDTO).collect(Collectors.toList());

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roleDTOs, HttpStatus.OK);
    }
}
