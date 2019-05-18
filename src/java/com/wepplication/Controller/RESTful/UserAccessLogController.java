package com.wepplication.Controller.RESTful;

import com.wepplication.Controller.RESTful.Service.UserAccessLogService;
import com.wepplication.Domain.UserAccessLog;
import com.wepplication.Util.DateTimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userlog")
public class UserAccessLogController {

    @Resource(name = "userLogService")
    private UserAccessLogService userAccessLogService;

    // 로그 생성
    @CrossOrigin
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity<UserAccessLog> usersPost(@RequestBody UserAccessLog userAccessLog) {
        if (userAccessLog == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println("Insert user_log");
        try {
            userAccessLog.setReqTime(DateTimeUtil.now());
            return new ResponseEntity<>(userAccessLogService.saveUserLog(userAccessLog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 로그 조회
}
