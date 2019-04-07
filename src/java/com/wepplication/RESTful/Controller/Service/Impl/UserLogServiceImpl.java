package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.UserLogService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.UserLog;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Exception.UnauthorizedException;
import com.wepplication.RESTful.Repository.UserLogDAO;
import com.wepplication.RESTful.Repository.UsersDAO;
import com.wepplication.Util.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.List;

@Service("userLogService")
public class UserLogServiceImpl implements UserLogService {
    // DAO
    @Resource(name = "userLogDAO")
    UserLogDAO userLogDAO;

}
