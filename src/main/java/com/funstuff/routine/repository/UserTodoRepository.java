package com.funstuff.routine.repository;

import com.funstuff.routine.entity.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTodoRepository extends JpaRepository<UserTodo, Long> {
   UserTodo findUserTodoById(long id);

   @Query(value = "select * from userTodos ut where ut.user_id = ?1",nativeQuery = true)
   List<UserTodo> findAllByUserId(long id);

   @Override
   List<UserTodo> findAll();
}
