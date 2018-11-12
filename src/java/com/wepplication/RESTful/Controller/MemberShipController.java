package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.MemberShipService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.MemberShip;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/membership")
public class MemberShipController {

    @Resource(name = "memberShipService")
    private MemberShipService memberShipService;

    @RequestMapping("/{mno}")
    public MemberShip memberShipGet(@PathVariable(value="mno") Integer mno) {
        System.out.println(mno);
        try{
            return memberShipService.findMemberShipByMno(mno);
        } catch (Exception e) {
            return null;
        }
    }
}
