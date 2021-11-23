package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class ConflictException extends ResponseStatusException {
        public ConflictException() {
            super(HttpStatus.CONFLICT, "Conflict");
        }
    }
