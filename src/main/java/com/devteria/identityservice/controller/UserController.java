package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    User handleCreate(@RequestBody UserCreationRequest request) {
        return userService.create(request);
    }

    @PutMapping("/{id}")
    User handleUpdate(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    String handleDelete(@PathVariable String id) {
        userService.delete(id);
        return "user has been deleted";
    }

    @GetMapping
    List<User> handleFindAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    User handleFindOne(@PathVariable String id) {
        return userService.findOne(id);
    }
}

// master thêm code để config cho thú vị nè =))

/* Upgrade:
- Format json: status, message, data/error
- Display exception on json (not console)
- Display number of records that are updated/ deleted
*/
