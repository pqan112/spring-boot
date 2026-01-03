package com.example.demo.service.impl;

import com.example.demo.dto.req.UserReqDTO;
import com.example.demo.dto.req.UserUpdateReqDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(UserReqDTO req) {
        User user = new User();

        if(userRepository.existsByUsername(req.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User existed.");
        }

        user.setUsername(req.getUsername());
        user.setFirstName(req.getFirstName());
        user.setPassword(req.getPassword());
        user.setLastName(req.getLastName());
        user.setDob(req.getDob());

        return userRepository.save(user);

    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(UUID userId) {
        return getUserById(userId);
    }

    @Override
    public User updateUser(UUID userId, UserUpdateReqDTO req) {
        User user =getUserById(userId);

        if (req.getPassword() != null) user.setPassword(req.getPassword());
        if (req.getDob() != null) user.setDob(req.getDob());
        if (req.getLastName() != null) user.setLastName(req.getLastName());
        if (req.getFirstName() != null) user.setFirstName(req.getFirstName());

        return userRepository.save(user);

    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
