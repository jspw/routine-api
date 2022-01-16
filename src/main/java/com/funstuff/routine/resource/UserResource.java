package com.funstuff.routine.resource;

import com.funstuff.routine.entity.User;
import com.funstuff.routine.request.SignupForm;
import com.funstuff.routine.request.UserUpdateForm;
import com.funstuff.routine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/",produces = {"application/json"})
    public ResponseEntity<?> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping(value = "/",consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<?> createUser(@RequestBody SignupForm signupFrom) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        User user = userService.createUser(signupFrom);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity<?>getUser(@PathVariable(name = "id") long id){
        User user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping(value = "/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateForm userUpdateData, @PathVariable(name = "id") long id ){
        User updatedUser = userService.updateUser(id,userUpdateData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
