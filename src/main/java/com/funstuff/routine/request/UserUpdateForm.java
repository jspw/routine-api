package com.funstuff.routine.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateForm {
    private String displayName;
    private String address;
    private String github;
    private String company;
}
