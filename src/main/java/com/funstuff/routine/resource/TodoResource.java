package com.funstuff.routine.resource;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.request.AddTodoForm;
import com.funstuff.routine.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoResource {
    private final TodoService todoService;

    @Autowired
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

//    @GetMapping(value = "/",produces = {"application/json"})
//    public ResponseEntity<?> getTodos(){
//        List<Todo> users = todoService.getTodos();
//        return ResponseEntity.status(HttpStatus.OK).body(users);
//    }

    @PostMapping(value = "/",consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<?> createTodo(@RequestBody AddTodoForm addTodoForm) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        Todo user = todoService.addTodo(addTodoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity<?>getTodo(@PathVariable(name = "id") long id){
        Todo user = todoService.getTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping(value = "/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> updateTodo(@RequestBody TodoUpdateForm userUpdateData,
                                        @PathVariable(name = "id") long id ){
        Todo updatedTodo = todoService.updateTodo(id,userUpdateData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable(name = "id") long id){
        todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
