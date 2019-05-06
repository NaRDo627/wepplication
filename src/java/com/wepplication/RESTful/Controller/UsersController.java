package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Exception.UnauthorizedException;
import com.wepplication.Util.DateTimeUtil;
import com.wepplication.Util.EncryptUtil;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource(name = "usersService")
    private UsersService usersService;

    // 유저 조회
    @CrossOrigin
    @RequestMapping(value = {"/check_duplicate_id/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> usersCheckDuplicateIdGet(@PathVariable(value="id") String id) {
        System.out.println("select count(users) where id="+id);
        try{
            JSONObject obj = new JSONObject();
            Integer recordCount = usersService.countUsersByUserId(id);
            if(recordCount > 0)
                obj.put("duplicate", true);
            else
                obj.put("duplicate", false);

            return new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = {"/check_duplicate_email/{email}"}, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> usersCheckDuplicateEmailGet(@PathVariable(value="email") String email) {
        System.out.println("select count(users) where email="+email);
        try{
            JSONObject obj = new JSONObject();
            Integer recordCount = usersService.countUsersByEmail(email);
            if(recordCount > 0)
                obj.put("duplicate", true);
            else
                obj.put("duplicate", false);

            return new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = {"/{uno}"}, method = RequestMethod.GET)
    public ResponseEntity<Users> usersGet(@PathVariable(value="uno") Integer uno) {
        System.out.println("select users where uno=" +uno);
        try{
            Users user = usersService.findUsersByUno(uno);
            if(user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 유저 생성
    @CrossOrigin
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity<Users> usersPost(@RequestBody Users users) {
        if(users == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        System.out.println("Insert " + users.getUserName());
        try{
            users.setPassword(EncryptUtil.encryptByMd5("whfwkrtlfjbb" + users.getPassword()));
            users.setVerified(0);
            users.setInsertTime(DateTimeUtil.now());
            users.setUpdateTime(DateTimeUtil.now());
            return new ResponseEntity<>(usersService.saveUser(users), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 유저 수정
    @CrossOrigin
    @RequestMapping(value = {"", "/"}, method = RequestMethod.PUT)
    public ResponseEntity<Users> usersPut(@RequestBody Users users) {
        if(users == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        System.out.println("Update " + users.getUserName());
        try{
            users.setUpdateTime(DateTimeUtil.now());
            return new ResponseEntity<>(usersService.saveUser(users), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 유저 인증
    @CrossOrigin
    @RequestMapping(value = {"/verification"}, method = RequestMethod.PUT)
    public ResponseEntity<Users> usersVerificationGet(@RequestHeader(value="authorization") String authorization) {
        if(authorization == null || authorization.length() == 0)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try{
            Users users = usersService.authentication(authorization);
            System.out.println("Update " + users.getUserName());
            users.setUpdateTime(DateTimeUtil.now());
            users.setVerified(1);
            return new ResponseEntity<>(usersService.saveUser(users), HttpStatus.OK);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 개인정보 조회
    @RequestMapping(value = {"/myinfo"}, method = RequestMethod.GET)
    public ResponseEntity<Users> usersMyinfoGet(@RequestHeader(value="authorization") String authorization) {
        if(authorization == null || authorization.length() == 0)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        System.out.println("select users by authorization");
        try{
            Users users = usersService.authentication(authorization);
            System.out.println(users.getUserName());
            return  new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
