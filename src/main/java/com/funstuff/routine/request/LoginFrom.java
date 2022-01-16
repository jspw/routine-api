package com.funstuff.routine.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginFrom {
    private String email;
    private String password;
}
