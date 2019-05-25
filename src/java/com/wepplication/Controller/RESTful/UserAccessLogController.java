package com.wepplication.Controller.RESTful;

import com.google.gson.JsonObject;
import com.wepplication.Controller.RESTful.Service.UserAccessLogService;
import com.wepplication.Domain.UserAccessLog;
import com.wepplication.Form.MorrisLogForm;
import com.wepplication.Util.DateTimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userlog")
public class UserAccessLogController {

    @Resource(name = "userLogService")
    private UserAccessLogService userAccessLogService;

    // 로그 생성
    @CrossOrigin
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity<UserAccessLog> userLogPost(@RequestBody UserAccessLog userAccessLog) {
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
    @CrossOrigin
    @RequestMapping(value = {"/users/{uno}"}, method = RequestMethod.GET)
    public ResponseEntity<List<MorrisLogForm>> userLogUsersGet(@PathVariable(name = "uno") Integer uno) {
        if (uno == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println("count user_access_log");
        try {
            return new ResponseEntity<>(userAccessLogService.countUserAccessLogByUno(uno), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
