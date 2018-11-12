package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Repository.UsersDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
    // DAO
    @Resource(name = "usersDAO")
    UsersDAO usersDAO;

    public Users findUsersByUno(Integer uno) throws Exception {
        return usersDAO.findOne(uno);
    }
}
