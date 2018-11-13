package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Util.DateTimeUtil;
import com.wepplication.RESTful.Util.EncryptUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource(name = "usersService")
    private UsersService usersService;

    // 유저 조회
    @RequestMapping(value = {"/{uno}"}, method = RequestMethod.GET)
    public Users usersGet(@PathVariable(value="uno") Integer uno) {
        System.out.println(uno);
        try{
            return usersService.findUsersByUno(uno);
        } catch (Exception e) {
            return null;
        }
    }

    // 유저 생성
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public Users usersPost(@RequestParam(value="user_id") String userId,
                           @RequestParam(value="password") String password,
                           @RequestParam(value="user_name") String userName,
                           @RequestParam(value="user_nickname") String userNickName,
                           @RequestParam(value="email") String email) {
        System.out.println("generating " + userName);
        try{
            Users users = new Users();
            users.setUserId(userId);
            users.setPassword(EncryptUtil.encryptByMd5("whfwkrtlfjbb" + password));
            users.setUserName(userName);
            users.setUserNickname(userNickName);
            users.setEmail(email);
            users.setMemberShipStatus(0);
            users.setInsertTime(DateTimeUtil.now());
            users.setUpdateTime(DateTimeUtil.now());
            return usersService.saveUser(users);
        } catch (Exception e) {
            return null;
        }
    }

    // 개인정보 조회
    @RequestMapping(value = {"/myinfo"}, method = RequestMethod.GET)
    public Users usersMyinfoGet(@RequestHeader(value="authorization") String authorization) {
        Users users = usersService.authentication(authorization);
        try{
            System.out.println(users.getUserName());
            return users;
        } catch (Exception e) {
            return null;
        }
    }
}
