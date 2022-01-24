package com.funstuff.routine.service;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.payload.request.AddTodoForm;
import com.funstuff.routine.payload.request.UpdateTodoForm;
import com.funstuff.routine.payload.request.UpdateTodoStatusForm;

import java.util.List;

public interface TodoService {

    Todo addTodo(AddTodoForm addTodoForm,long userId);

    List<Todo> getTodos();

    Todo updateTodo(long id, UpdateTodoForm updateTodoForm);

    Todo updateTodoStatus (long id, UpdateTodoStatusForm statusForm);

    Todo getTodo(long id);

    void deleteTodo(long id);

    List<Todo> getUserTodos(long userId);

    List<Todo> getUserPreviousTodos(long userId);

    List<Todo> getUserUpcomingTodos(long userId);

    List<Todo> getUserDailyTodos(long userId);

    List<Todo> getUserCurrentDailyTodos(long userId);

}
