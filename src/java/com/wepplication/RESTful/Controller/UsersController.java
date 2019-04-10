package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.Util.DateTimeUtil;
import com.wepplication.Util.EncryptUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource(name = "usersService")
    private UsersService usersService;

    // 유저 조회
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<Users> usersGet() {
        System.out.println("select users *");
        try{
            return usersService.findUsersAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = {"/{uno}"}, method = RequestMethod.GET)
    public Users usersGet(@PathVariable(value="uno") Integer uno) {
        System.out.println("select users where uno=" +uno);
        try{
            return usersService.findUsersByUno(uno);
        } catch (Exception e) {
            return null;
        }
    }

    // 유저 생성
    @CrossOrigin
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public Users usersPost(@RequestBody Users users) {
        if(users == null)
            return null;
        System.out.println("Insert " + users.getUserName());
        try{
            users.setPassword(EncryptUtil.encryptByMd5("whfwkrtlfjbb" + users.getPassword()));
            users.setMemberShipStatus(0);
            users.setInsertTime(DateTimeUtil.now());
            users.setUpdateTime(DateTimeUtil.now());
            return usersService.saveUser(users);
        } catch (Exception e) {
            return null;
        }
    }

    // 유저 수정
    @CrossOrigin
    @RequestMapping(value = {"", "/"}, method = RequestMethod.PUT)
    public Users usersPut(@RequestBody Users users) {
        if(users == null)
            return null;
        System.out.println("Update " + users.getUserName());
        try{
            users.setUpdateTime(DateTimeUtil.now());
            return usersService.saveUser(users);
        } catch (Exception e) {
            return null;
        }
    }

    // 개인정보 조회
    @RequestMapping(value = {"/myinfo"}, method = RequestMethod.GET)
    public Users usersMyinfoGet(@RequestHeader(value="authorization") String authorization) {
        System.out.println("select users by authorization");
        Users users = usersService.authentication(authorization);
        try{
            System.out.println(users.getUserName());
            return users;
        } catch (Exception e) {
            return null;
        }
    }
}
