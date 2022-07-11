package com.haanbhai.blogs.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
// Repositories will not be exposed directly, we will use DTOs for exposing to services etc.
public class UserDTO {
    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username should of min 4 characters")
    private String name;
    @Email(message = "Email address not valid")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,10}$", message = "Password must have length between 4 to 10, atleast one letter and one number")
    private String password;
    @NotEmpty
    private String about;
    private boolean isActive;
}
