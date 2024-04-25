package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.response.ApiResponse;
import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // dua bien final vao constructor de cam cac dependency vao // autowired ko phai la best practice
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> handleCreate(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("create a new user");
        apiResponse.setResult(this.userService.create(request));
        return apiResponse;
        // advance: set message before go into this method
    }

    @PutMapping("/{id}")
    UserResponse handleUpdate(@PathVariable String id, @RequestBody UserUpdateRequest request) {
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
    ApiResponse<UserResponse> handleFindOne(@PathVariable String id) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("find all users");
        apiResponse.setResult(this.userService.findOne(id));
        return apiResponse;
    }
}

// master thêm code để config cho thú vị nè =))

/* Upgrade:
- Format json: status, message, data/error
- Display exception on json (not console)
- Display number of records that are updated/ deleted
*/

// toi la dev 1 ne
