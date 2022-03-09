package com.funstuff.routine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.funstuff.routine.utility.enums.TodoStatus;
import com.funstuff.routine.utility.enums.TodoType;
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

    @Column(name= "detail",columnDefinition = "TEXT",length = 1000)
    private String detail;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status",nullable = false)
    private TodoStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="type",nullable=false)
    private TodoType type;

    @Column(name = "start_at",nullable = false)
    private Date startAt;

    @Column(name = "end_at",nullable = false)
    private Date endAt;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
