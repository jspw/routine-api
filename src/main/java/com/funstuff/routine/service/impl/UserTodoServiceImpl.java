package com.funstuff.routine.service.impl;

import com.funstuff.routine.dto.TodoDto;
import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.repository.UserTodoRepository;
import com.funstuff.routine.request.AddTodoForm;
import com.funstuff.routine.service.TodoService;
import com.funstuff.routine.service.UserTodoService;
import com.funstuff.routine.utility.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTodoServiceImpl extends UserTodoService {
    @Autowired
    private UserTodoRepository userTodoRepository;

    @Autowired
    private TodoService todoService;

    @Override
    public TodoDto getTodo(long id) {
        Todo todo =  todoService.getTodo(id) ;
        TodoDto todoDto = new TodoDto();

        return ;
    }

    @Override
    public TodoDto addTodo(long userId, AddTodoForm addTodoForm) {
        return null;
    }

    @Override
    public TodoDto update(long todoId, AddTodoForm addTodoForm) {
        return null;
    }

    @Override
    public TodoDto updateTodoStatus(long todoId, TodoStatus status) {
        return null;
    }

    @Override
    public List<TodoDto> getUserTodos(long userId) {
        return null;
    }

    @Override
    public List<TodoDto> getUserDailyTodosForToday(long userId) {
        return null;
    }

    @Override
    public List<TodoDto> getUserPreviousTodos(long userId) {
        return null;
    }

    @Override
    public List<TodoDto> getUserUpcomingTodos(long userId) {
        return null;
    }

    @Override
    public void deleteTodo(long id) {

    }
}
