package com.wepplication.RESTful.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED, reason = "IM_USED")
public class ImUsedException extends RuntimeException {
    public ImUsedException(String msg) {
        super(msg);
    }
}
