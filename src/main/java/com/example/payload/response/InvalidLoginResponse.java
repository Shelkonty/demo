package com.example.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {

    private String usernames;
    private String password;

        public InvalidLoginResponse(){
            this.usernames = "Invalid Username";
            this.password = "Invalid Password";
        }
}
