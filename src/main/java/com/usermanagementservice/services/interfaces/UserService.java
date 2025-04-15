package com.usermanagementservice.services.interfaces;

import com.usermanagementservice.dtos.requests.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto dto);

    UserDto getUserById(UUID id);

    UserDto updateUser(UUID id, UserDto dto);

    void deleteUser(UUID id);
}
