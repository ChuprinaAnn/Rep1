package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class BadRequestException extends ResponseStatusException {
        public BadRequestException() {
            super(HttpStatus.BAD_REQUEST, "Bad Request");
        }
}
