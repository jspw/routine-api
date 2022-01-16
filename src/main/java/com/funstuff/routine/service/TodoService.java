package com.funstuff.routine.service;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.repository.TodoRepository;
import com.funstuff.routine.request.AddTodoForm;
import com.funstuff.routine.request.UpdateTodoForm;
import org.springframework.beans.factory.annotation.Autowired;

public interface TodoService {

    Todo addTodo(AddTodoForm addTodoForm);

    Todo updateTodo(long id, UpdateTodoForm updateTodoForm);

    Todo getTodo(long id);

    void deleteTodo(long id);

}
