package com.funstuff.routine.payload.request;

import com.funstuff.routine.utility.enums.TodoStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateTodoStatusForm {
    private TodoStatus status;
}
