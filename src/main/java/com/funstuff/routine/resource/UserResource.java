package com.funstuff.routine.resource;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.entity.User;
import com.funstuff.routine.payload.request.SignupForm;
import com.funstuff.routine.payload.request.UserUpdateForm;
import com.funstuff.routine.security.UserDetailsImpl;
import com.funstuff.routine.service.TodoService;
import com.funstuff.routine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private  UserService userService;

    @Autowired
    private  TodoService todoService;

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


    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        UserDetailsImpl user = (UserDetailsImpl)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userService.getUser(user.getId()));
    }
}
