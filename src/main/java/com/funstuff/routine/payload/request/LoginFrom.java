package com.funstuff.routine.payload.request;

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
