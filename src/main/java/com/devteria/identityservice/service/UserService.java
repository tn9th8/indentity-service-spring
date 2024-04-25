package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.UserMapper;
import com.devteria.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // dua bien final vao constructor de cam cac dependency vao // autowired ko phai la best practice
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    // create
    public User create(UserCreationRequest request) {
        // is Exist ?
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXIST);

        // create
        User user = userMapper.toUser(request);

        // save
        return userRepository.save(user);
    }

    // update by id
    public UserResponse update(String id, UserUpdateRequest request) {
        // find one + update
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        // save
        return userMapper.toUserResponse(userRepository.save(user));
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
    public UserResponse findOne(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }
}
