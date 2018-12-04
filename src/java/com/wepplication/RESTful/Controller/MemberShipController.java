package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.MemberShipService;
import com.wepplication.RESTful.Domain.MemberShip;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/membership")
public class MemberShipController {

    @Resource(name = "memberShipService")
    private MemberShipService memberShipService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<MemberShip> memberShipGet() {
        System.out.println("select membership *");
        try{
            return memberShipService.findMemberShipAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = {"/{mno}"}, method = RequestMethod.GET)
    public MemberShip memberShipGet(@PathVariable(value="mno") Integer mno) {
        System.out.println("select membership where mno=" + mno);
        try{
            return memberShipService.findMemberShipByMno(mno);
        } catch (Exception e) {
            return null;
        }
    }
}
