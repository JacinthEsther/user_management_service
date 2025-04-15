package com.usermanagementservice.dtos.requests;


import lombok.*;

import java.util.UUID;



@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String role;

}
