package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UserMemberShipService;
import com.wepplication.RESTful.Domain.UserMemberShip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user_membership")
public class UserMemberShipController {

    @Resource(name = "userMemberShipService")
    private UserMemberShipService userMemberShipService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<List<UserMemberShip>> userMemberShipGet() {
        System.out.println("select user_membership *");
        try{
            List<UserMemberShip> userMemberShipList = userMemberShipService.findUserMemberShipAll();
            if(userMemberShipList == null || userMemberShipList.size() == 0)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(userMemberShipList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = {"/{umno}"}, method = RequestMethod.GET)
    public ResponseEntity<UserMemberShip> userMemberShipGet(@PathVariable(value="umno") Integer umno) {
        System.out.println("select user_membership where umno=" + umno);
        try{
            UserMemberShip userMemberShip = userMemberShipService.findUserMemberShipByUmno(umno);
            if(userMemberShip == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(userMemberShip, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity<UserMemberShip> userMemberShipPost(@RequestBody UserMemberShip userMemberShip) {
        System.out.println("insert user_membership");
        try{
            return new ResponseEntity<>(userMemberShipService.saveUserMemberShip(userMemberShip), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
