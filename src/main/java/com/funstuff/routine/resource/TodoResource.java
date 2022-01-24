package com.funstuff.routine.resource;

import com.funstuff.routine.entity.Todo;
import com.funstuff.routine.payload.request.AddTodoForm;
import com.funstuff.routine.payload.request.UpdateTodoForm;
import com.funstuff.routine.payload.request.UpdateTodoStatusForm;
import com.funstuff.routine.security.UserDetailsImpl;
import com.funstuff.routine.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoResource {
    private final TodoService todoService;

    @Autowired
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<?> getTodos(){
        return  ResponseEntity.ok().body(todoService.getTodos());
    }

    @PostMapping(value = "/",consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<?> createTodo(@RequestBody AddTodoForm addTodoForm)
             {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Todo user = todoService.addTodo(addTodoForm,userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity<?>getTodo(@PathVariable(name = "id") long id){
        Todo user = todoService.getTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping(value = "/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> updateTodo(@RequestBody UpdateTodoForm todoUpdateData,
                                        @PathVariable(name = "id") long id ){
        Todo updatedTodo = todoService.updateTodo(id,todoUpdateData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

    @PatchMapping(value = "/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> updateTodoStatus(@RequestBody UpdateTodoStatusForm todoStatusForm,
                                        @PathVariable(name = "id") long id ){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodoStatus(id,todoStatusForm));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable(name = "id") long id){
        todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
