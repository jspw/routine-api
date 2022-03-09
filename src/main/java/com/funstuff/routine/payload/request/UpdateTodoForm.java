package com.funstuff.routine.payload.request;

import com.funstuff.routine.utility.enums.TodoType;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateTodoForm {
    private String title;
    private String detail;
    private TodoType type;
    private Date startAt;
    private Date endAt;
}
