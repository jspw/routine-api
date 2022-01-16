package com.funstuff.routine.repository;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    Todo findTodoById(Long id);

    @Override
    List<Todo> findAll();

}
