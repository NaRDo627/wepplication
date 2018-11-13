package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UserMemberShipService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.UserMemberShip;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user_membership")
public class UserMemberShipController {

    @Resource(name = "userMemberShipService")
    private UserMemberShipService userMemberShipService;

    @RequestMapping(value = {"/{umno}"}, method = RequestMethod.GET)
    public UserMemberShip userMemberShipGet(@PathVariable(value="umno") Integer umno) {
        System.out.println(umno);
        try{
            return userMemberShipService.findMemberShipByUmno(umno);
        } catch (Exception e) {
            return null;
        }
    }
}
