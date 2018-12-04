package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.UserCloudLocService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.UserCloudLoc;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user_cloud_loc")
public class UserCloudLocController {

    @Resource(name = "userCloudLocService")
    private UserCloudLocService userCloudLocService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<UserCloudLoc> userCloudLocGet() {
        System.out.println("select user_cloud_loc *");
        try{
            return userCloudLocService.findUserCloudLocAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = {"/{lno}"}, method = RequestMethod.GET)
    public UserCloudLoc userCloudLocGet(@PathVariable(value="lno") Integer lno) {
        System.out.println("select user_cloud_loc where lno=" + lno);
        try{
            return userCloudLocService.findUserCloudLocByLno(lno);
        } catch (Exception e) {
            return null;
        }
    }
}
