package com.funstuff.routine.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private long id;

    private String username;

    private String displayName;

    private String address;

    private String email;

    private String github;

    private String company;

    private Date createdAt;

}
