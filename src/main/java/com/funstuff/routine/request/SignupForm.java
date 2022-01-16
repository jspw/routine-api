package com.funstuff.routine.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public  class SignupForm {
    private String username;
    private String displayName;
    private String address;
    private String email;
    private String github;
    private String company;
    private String password;

}