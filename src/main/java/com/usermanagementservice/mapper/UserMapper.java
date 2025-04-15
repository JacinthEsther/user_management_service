package com.usermanagementservice.mapper;

import com.usermanagementservice.dtos.requests.UserDto;
import com.usermanagementservice.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);


    User toEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}
