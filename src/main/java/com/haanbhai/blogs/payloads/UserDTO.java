package com.haanbhai.blogs.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
// Repositories will not be exposed directly, we will use DTOs for exposing to services etc.
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    private boolean isActive;
}
