package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.UserCloudLocService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.UserCloudLoc;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Repository.UserCloudLocDAO;
import com.wepplication.RESTful.Repository.UsersDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userCloudLocService")
public class UserCloudLocServiceImpl implements UserCloudLocService {
    // DAO
    @Resource(name = "userCloudLocDAO")
    UserCloudLocDAO userCloudLocDAO;

    public UserCloudLoc findUserCloudLocByLno(Integer lno) throws Exception {
        return userCloudLocDAO.findOne(lno);
    }
}
