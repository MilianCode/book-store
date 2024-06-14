package com.miliancode.service;

import com.miliancode.dto.user.UserRegistrationRequestDto;
import com.miliancode.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
