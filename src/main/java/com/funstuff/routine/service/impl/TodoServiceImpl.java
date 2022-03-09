package com.funstuff.routine.service.impl;

import com.funstuff.routine.Exception.CustomException;
import com.funstuff.routine.Exception.ResourceAlreadyExistException;
import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.entity.User;
import com.funstuff.routine.payload.request.UpdateTodoStatusForm;
import com.funstuff.routine.repository.TodoRepository;
import com.funstuff.routine.repository.UserRepository;
import com.funstuff.routine.payload.request.AddTodoForm;
import com.funstuff.routine.payload.request.UpdateTodoForm;
import com.funstuff.routine.service.TodoService;
import com.funstuff.routine.utility.enums.TodoStatus;
import com.funstuff.routine.utility.enums.TodoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Todo addTodo(AddTodoForm addTodoForm,long userId) {

       if( todoRepository.findTodoByTitle(addTodoForm.getTitle())!= null) throw new ResourceAlreadyExistException(
               "New todo title '"+ addTodoForm.getTitle()+ "' already exists");

        Todo todo = new Todo();
        todo.setTitle(addTodoForm.getTitle());
        todo.setDetail(addTodoForm.getDetail());
        todo.setStartAt(addTodoForm.getStartAt());
        todo.setEndAt(addTodoForm.getEndAt());
        todo.setCreatedAt(Date.valueOf(LocalDate.now()));
        todo.setType(addTodoForm.getType());
        todo.setStatus(TodoStatus.PENDING);
        User user = userRepository.findUserById(userId);
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
        if(todo==null) throw new CustomException("TodoId: " +id +" not found", HttpStatus.NOT_FOUND);
        todo.setTitle(updateTodoForm.getTitle());
        todo.setDetail(updateTodoForm.getDetail());
        todo.setStartAt(updateTodoForm.getStartAt());
        todo.setEndAt(updateTodoForm.getEndAt());
        todo.setUpdatedAt(Date.valueOf(LocalDate.now()));
        todo.setType(updateTodoForm.getType());
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodoStatus(long id, UpdateTodoStatusForm statusForm) {
        Todo todo = todoRepository.findTodoById(id);
        if(todo==null) throw new CustomException("TodoId: " +id +" not found",HttpStatus.NOT_FOUND);
        todo.setStatus(statusForm.getStatus());
        todo.setUpdatedAt(Date.valueOf(LocalDate.now()));
        return todoRepository.save(todo);
    }


    @Override
    public Todo getTodo(long id) {
        Todo todo =todoRepository.findTodoById(id);
        if(todo==null) throw new CustomException("TodoId: " +id +" not found",HttpStatus.NOT_FOUND);
        return todo ;
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
    public List<Todo> getUserCurrentDailyTodos(long userId) {
        return  todoRepository.findUserTodoByDateAndType(userId,Date.valueOf(LocalDate.now()),TodoType.DAILY);
    }

    @Override
    public List<Todo> getUserCurrentTodos(long userId) {
        return todoRepository.findUserTodoByDate(userId,Date.valueOf(LocalDate.now()));
    }
}
