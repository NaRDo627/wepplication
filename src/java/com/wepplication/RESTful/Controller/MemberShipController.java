package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.MemberShipService;
import com.wepplication.RESTful.Domain.MemberShip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/membership")
public class MemberShipController {

    @Resource(name = "memberShipService")
    private MemberShipService memberShipService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<List<MemberShip>> memberShipGet() {
        System.out.println("select membership *");
        try{
            List<MemberShip> memberShipList = memberShipService.findMemberShipAll();
            if(memberShipList == null || memberShipList.size() == 0)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(memberShipList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity<MemberShip> memberShipPost(@RequestBody MemberShip memberShip) {
        System.out.println("insert membership");
        try{
            return new ResponseEntity<>(memberShipService.saveMemberShip(memberShip), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/{mno}"}, method = RequestMethod.GET)
    public ResponseEntity<MemberShip> memberShipGet(@PathVariable(value="mno") Integer mno) {
        System.out.println("select membership where mno=" + mno);
        try{
            MemberShip memberShip = memberShipService.findMemberShipByMno(mno);
            if(memberShip == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(memberShip, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
