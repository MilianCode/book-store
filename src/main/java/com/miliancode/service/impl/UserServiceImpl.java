package com.miliancode.service.impl;

import com.miliancode.dto.user.UserRegistrationRequestDto;
import com.miliancode.dto.user.UserResponseDto;
import com.miliancode.exception.RegistrationException;
import com.miliancode.mapper.UserMapper;
import com.miliancode.model.User;
import com.miliancode.repository.UserRepository;
import com.miliancode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with this email is already registered");
        }
        User user = userMapper.toModel(requestDto);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
