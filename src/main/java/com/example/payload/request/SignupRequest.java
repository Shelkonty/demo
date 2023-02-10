package com.example.payload.request;


import com.example.annotations.PasswordMatches;
import com.example.annotations.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "please, enter your username")
    private String usernames;
    @NotEmpty(message = "please, enter your firstname")
    private String f_name;
    @NotEmpty(message = "please, enter your lastname")
    private String l_name;
    @NotEmpty(message = "please, enter your lastname")
    private String phone;
    @NotEmpty(message = "please, enter your password")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}

