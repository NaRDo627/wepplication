package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UserMemberShipService;
import com.wepplication.RESTful.Domain.UserMemberShip;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user_membership")
public class UserMemberShipController {

    @Resource(name = "userMemberShipService")
    private UserMemberShipService userMemberShipService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<UserMemberShip> userMemberShipGet() {
        System.out.println("select user_membership *");
        try{
            return userMemberShipService.findUserMemberShipAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = {"/{umno}"}, method = RequestMethod.GET)
    public UserMemberShip userMemberShipGet(@PathVariable(value="umno") Integer umno) {
        System.out.println("select user_membership where umno=" + umno);
        try{
            return userMemberShipService.findUserMemberShipByUmno(umno);
        } catch (Exception e) {
            return null;
        }
    }
}
