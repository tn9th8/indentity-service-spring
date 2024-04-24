package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ApiResponse<User> apiResponse;

    @PostMapping
    ApiResponse<User> handleCreate(@RequestBody @Valid UserCreationRequest request) {
        this.apiResponse.setMessage("create a new user");
        this.apiResponse.setResult(this.userService.create(request));
        return this.apiResponse;
        // advance: set message before go into this method
    }

    @PutMapping("/{id}")
    User handleUpdate(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return this.userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    String handleDelete(@PathVariable String id) {
        this.userService.delete(id);
        return "user has been deleted";
    }

    @GetMapping
    ApiResponse<List<User>> handleFindAll() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("find all users");
        apiResponse.setResult(this.userService.findAll());
        return apiResponse;
    }

    @GetMapping("/{id}")
    User handleFindOne(@PathVariable String id) {
        return this.userService.findOne(id);
    }
}

/* Upgrade:
- Format json: status, message, data/error
- Display exception on json (not console)
- Display number of records that are updated/ deleted
*/

// toi la dev 1 ne
