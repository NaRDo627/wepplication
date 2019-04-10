package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UserLogService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.UserLog;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.Util.DateTimeUtil;
import com.wepplication.Util.EncryptUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userlog")
public class UserLogController {

    @Resource(name = "userLogService")
    private UserLogService userLogService;

    // 로그 생성
    @CrossOrigin
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public UserLog usersPost(@RequestBody UserLog userLog) {
        if (userLog == null)
            return null;
        System.out.println("Insert " + userLog.getUno());
        try {
            userLog.setReqTime(DateTimeUtil.now());
            return userLogService.saveUserLog(userLog);
        } catch (Exception e) {
            return null;
        }
    }

    // 로그 조회
}
