package com.funstuff.routine.entity;

import com.funstuff.routine.utility.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="userTodo")
@Table(name= " userTodos")
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class UserTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_id",nullable = false)
    private long userId;

    @Column(name="todo_id",nullable = false)
    private long todoId;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status",nullable = false)
    private TodoStatus status;

    @Column(name = "updated_at")
    private Date updatedAt;

}
