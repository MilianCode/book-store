package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.user.UserRegistrationRequestDto;
import com.miliancode.dto.user.UserResponseDto;
import com.miliancode.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {

    User toModel(UserRegistrationRequestDto requestDto);

    UserResponseDto toUserResponse(User user);
}
