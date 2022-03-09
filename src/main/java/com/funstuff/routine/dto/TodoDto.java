package com.funstuff.routine.dto;

import com.funstuff.routine.utility.enums.TodoStatus;
import com.funstuff.routine.utility.enums.TodoType;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TodoDto {
    private long id;
    private String title;
    private String detail;
    private TodoType type;
    private TodoStatus status;
    private Date startAt;
    private Date endAt;
    private Date updatedAt;
    private long userId;
}
