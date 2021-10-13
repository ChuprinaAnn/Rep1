package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
    @RestController
    public class ApiСontroller {
        List<String> messages = new ArrayList<>();
        @GetMapping("books")
        public ResponseEntity<String> getText() {
            return ResponseEntity.ok("Hello text");
        }

     @PostMapping("messages")
     public void addMessage (@RequestBody String message) {
            messages.add(message);
     }
    @GetMapping("messages/{i}")
    public String getMessage (@PathVariable("i") Integer index) {
            return messages.get(index);
    }


    @GetMapping("messages")
    public String getMessages() {
        String s = "";
        for (String message : messages) {
            s += message;
        }
        return s;
    }
}
