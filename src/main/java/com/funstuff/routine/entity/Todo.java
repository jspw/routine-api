package com.funstuff.routine.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.funstuff.routine.utility.TodoStatus;
import com.funstuff.routine.utility.TodoType;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="todo")
@Table(name = "todos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name ="title",nullable=false,unique=true)
    private String title;

    @Column(name= "detail")
    private String detail;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status",nullable = false)
    private TodoStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="type",nullable=false)
    private TodoType type;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
