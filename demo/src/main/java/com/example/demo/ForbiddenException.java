package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class ForbiddenException extends ResponseStatusException {
        public ForbiddenException() {
            super(HttpStatus.FORBIDDEN, "Forbidden");
        }
    }


