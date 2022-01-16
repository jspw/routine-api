package com.funstuff.routine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
@Table(name = "users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private long id;

   @Column(name = "username",nullable = false,unique = true,length = 100)
   private String username;

   @Column(name = "display_name",length = 255)
   private String displayName;

   @Column(name = "address")
    private String address;

   @Column(name = "email_address",nullable = false,unique = true)
   private String email;

   @Column(name = "github")
    private String github;

   @Column (name = "company")
    private String company;

   @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;
}
