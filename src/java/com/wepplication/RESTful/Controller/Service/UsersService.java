package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.Users;

public interface UsersService {
    Users findUsersByUno(Integer uno) throws Exception;
}
