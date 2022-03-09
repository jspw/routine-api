package com.funstuff.routine.repository;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.utility.enums.TodoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    Todo findTodoById(Long id);

    Todo findTodoByTitle(String title);

    @Override
    List<Todo> findAll();

    @Query(value = "select * from todos ut where ut.user_id = ?1",nativeQuery = true)
    List<Todo> findAllByUserId(long id);

    @Query(value = "select * from todos td where td.user_id=?1 and td.type=?2" ,nativeQuery = true)
    List<Todo> findUserTodoByType( long id, TodoType type);

    @Query(value = "select * from todos td where td.user_id=?1 and td.start_at=?2" ,nativeQuery = true)
    List<Todo> findUserTodoByDate( long id ,Date date);

    @Query(value = "select * from todos td where td.user_id=?1 and td.start_at=?2 and td.type=?3" ,nativeQuery = true)
    List<Todo> findUserTodoByDateAndType(long userId,Date date,TodoType todoType);

    @Query(value = "select * from todos td where td.user_id=?1 and td.end_at<?2" ,nativeQuery = true)
    List<Todo> findUserTodoByPreviousDate( long id ,Date date);

    @Query(value = "select * from todos td where td.user_id=?1 and td.start_at>?2" ,nativeQuery = true)
    List<Todo> findUserTodoByUpcomingDate( long id ,Date date);

}
