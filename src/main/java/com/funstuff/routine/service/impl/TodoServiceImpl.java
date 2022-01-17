package com.funstuff.routine.service.impl;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.entity.User;
import com.funstuff.routine.repository.TodoRepository;
import com.funstuff.routine.repository.UserRepository;
import com.funstuff.routine.request.AddTodoForm;
import com.funstuff.routine.request.UpdateTodoForm;
import com.funstuff.routine.service.TodoService;
import com.funstuff.routine.service.UserService;
import com.funstuff.routine.utility.TodoStatus;
import com.funstuff.routine.utility.TodoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Todo addTodo(AddTodoForm addTodoForm) {
        Todo todo = new Todo();
        todo.setTitle(addTodoForm.getTitle());
        todo.setDetail(addTodoForm.getDetail());
        todo.setStartAt(addTodoForm.getStartAt());
        todo.setEndAt(addTodoForm.getEndAt());
        todo.setCreatedAt(Date.valueOf(LocalDate.now()));
        todo.setType(addTodoForm.getType());
        todo.setStatus(TodoStatus.PENDING);
        User user = userRepository.findUserById(addTodoForm.getUserId());
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getTodos() {
        return todoRepository.findAll();
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
        todo.setStatus(updateTodoForm.getStatus());
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

    @Override
    public List<Todo> getUserTodos(long userId) {
        return todoRepository.findAllByUserId(userId);
    }

    @Override
    public List<Todo> getUserPreviousTodos(long userId) {
        return todoRepository.findUserTodoByPreviousDate(userId, Date.valueOf(LocalDate.now()));
    }

    @Override
    public List<Todo> getUserUpcomingTodos(long userId) {
        return todoRepository.findUserTodoByUpcomingDate(userId, Date.valueOf(LocalDate.now()));
    }

    @Override
    public List<Todo> getUserDailyTodos(long userId) {
        return  todoRepository.findUserTodoByType(userId,TodoType.DAILY);
    }

    @Override
    public List<Todo> getUserCurrentDailyTodos(long userId) {
        return todoRepository.findUserTodoByDate(userId,Date.valueOf(LocalDate.now()));
    }
}
