package com.funstuff.routine.resource;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultResource {
    @GetMapping
    ResponseEntity<?> getHome(){
        return ResponseEntity.status(HttpStatus.OK).body("Vai ami bachi asi....!");
    }
}
