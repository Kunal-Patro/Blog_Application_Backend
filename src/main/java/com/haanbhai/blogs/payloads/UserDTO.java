package com.haanbhai.blogs.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
// Repositories will not be exposed directly, we will use DTOs for exposing to services etc.
public class UserDTO {
    private Integer id;
    @NotEmpty
    @Size(min = 4, message = "Username should of min 4 characters")
    private String name;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email address not valid")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,10}$", message = "Password must have length between 4 to 10, atleast one letter and one number")
    private String password;
    @NotEmpty
    private String about;
    private boolean isActive;

    private Set<RoleDTO> roles = new HashSet<>();
}
