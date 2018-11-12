package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UserCloudLocService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.UserCloudLoc;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user_cloud_loc")
public class UserCloudLocController {

    @Resource(name = "userCloudLocService")
    private UserCloudLocService userCloudLocService;

    @RequestMapping("/{lno}")
    public UserCloudLoc userCloudLocGet(@PathVariable(value="lno") Integer lno) {
        System.out.println(lno);
        try{
            return userCloudLocService.findUserCloudLocByLno(lno);
        } catch (Exception e) {
            return null;
        }
    }
}
