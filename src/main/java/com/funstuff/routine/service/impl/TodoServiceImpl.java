package com.funstuff.routine.service.impl;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.repository.TodoRepository;
import com.funstuff.routine.request.AddTodoForm;
import com.funstuff.routine.request.UpdateTodoForm;
import com.funstuff.routine.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo addTodo(AddTodoForm addTodoForm) {
        Todo todo = new Todo();
        todo.setTitle(addTodoForm.getTitle());
        todo.setDetail(addTodoForm.getDetail());
        todo.setStartAt(addTodoForm.getStartAt());
        todo.setEndAt(addTodoForm.getEndAt());
        todo.setCreatedAt(Date.valueOf(LocalDate.now()));
        todo.setType(addTodoForm.getType());
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(long id, UpdateTodoForm updateTodoForm) {
        Todo todo = todoRepository.findTodoById(id);
        todo.setTitle(updateTodoForm.getTitle());
        todo.setDetail(updateTodoForm.getDetail());
        todo.setStartAt(updateTodoForm.getStartAt());
        todo.setEndAt(updateTodoForm.getEndAt());
        todo.setUpdatedAt(Date.valueOf(LocalDate.now()));
        todo.setType(updateTodoForm.getType());
        return todoRepository.save(todo);
    }

    @Override
    public Todo getTodo(long id) {
        return todoRepository.findTodoById(id);
    }

    @Override
    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }
}
