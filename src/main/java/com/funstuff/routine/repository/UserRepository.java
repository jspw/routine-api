package com.funstuff.routine.repository;

import com.funstuff.routine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(Long id);
    
    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Override
    List<User> findAll();



}
