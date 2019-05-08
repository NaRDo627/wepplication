package com.wepplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already Exists")
public class AlreadyExistsException extends RuntimeException {
    AlreadyExistsException(String msg){
        super(msg);
    }
}
