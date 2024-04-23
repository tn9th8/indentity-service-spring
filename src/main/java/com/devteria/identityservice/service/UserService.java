package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    // create
    public User create(UserCreationRequest request) {
        // create
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        // save
        return userRepository.save(user);
    }

    // update by id
    public User update(String id, UserUpdateRequest request) {
        // find one + update
        User user = findOne(id);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        // save
        return userRepository.save(user);
    }

    // delete by id
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    // find all
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // find by id
    // output: optional. Nên dùng lambda bắt exception, hiển thị ra console. Nên hiển thị ra json
    public User findOne(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
