package com.funstuff.routine.service;



import com.funstuff.routine.entity.User;
import com.funstuff.routine.request.SignupForm;
import com.funstuff.routine.request.UserUpdateForm;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UserService {
    User createUser(SignupForm userForm) throws NoSuchAlgorithmException, InvalidKeySpecException;

    User updateUser(long id,  UserUpdateForm userUpdateForm);

    void deleteUser(long id);

    User getUser(long id);

    List<User> getUsers();



}
