package com.miliancode.service.impl;

import com.miliancode.dto.user.UserRegistrationRequestDto;
import com.miliancode.dto.user.UserResponseDto;
import com.miliancode.exception.RegistrationException;
import com.miliancode.mapper.UserMapper;
import com.miliancode.model.Role;
import com.miliancode.model.User;
import com.miliancode.repository.RoleRepository;
import com.miliancode.repository.UserRepository;
import com.miliancode.service.ShoppingCartService;
import com.miliancode.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with this email is already registered");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
        shoppingCartService.createShoppingCart(user);
        return userMapper.toUserResponse(user);
    }
}
