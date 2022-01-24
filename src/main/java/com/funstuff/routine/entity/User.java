package com.funstuff.routine.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private long id;

   @Column(name = "username",nullable = false,unique = true,length = 100)
   private String username;

   @ManyToMany(fetch = FetchType.EAGER)
   private Collection<Role> roles =new ArrayList<>() ;

   @Column(name = "display_name")
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

    @Column(name = "updated_At")
    private Date updatedAt;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Todo> todos;
}
