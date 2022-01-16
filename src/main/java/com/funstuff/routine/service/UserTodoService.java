package com.funstuff.routine.service;

import com.funstuff.routine.dto.TodoDto;
import com.funstuff.routine.request.AddTodoForm;
import com.funstuff.routine.utility.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserTodoService {
    TodoDto getTodo(long id);
    TodoDto addTodo(long userId, AddTodoForm addTodoForm);
    TodoDto update(long todoId, AddTodoForm addTodoForm);
    TodoDto updateTodoStatus (long todoId, TodoStatus status);
    List<TodoDto> getUserTodos(long userId);
    List<TodoDto> getUserDailyTodosForToday(long userId);
    List<TodoDto> getUserPreviousTodos(long userId);
    List<TodoDto> getUserUpcomingTodos(long userId);

    void deleteTodo(long id);

}
