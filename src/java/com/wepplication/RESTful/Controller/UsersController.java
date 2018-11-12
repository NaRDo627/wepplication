package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource(name = "usersService")
    private UsersService usersService;

    @RequestMapping("/{uno}")
    public Users usersGet(@PathVariable(value="uno") Integer uno) {
        System.out.println(uno);
        try{
            return usersService.findUsersByUno(uno);
        } catch (Exception e) {
            return null;
        }
    }
}
