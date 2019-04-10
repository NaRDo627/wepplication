package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.UserAccessLogService;
import com.wepplication.RESTful.Domain.UserAccessLog;
import com.wepplication.RESTful.Repository.UserAccessLogDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userLogService")
public class UserAccessLogServiceImpl implements UserAccessLogService {
    // DAO
    @Resource(name = "userLogDAO")
    UserAccessLogDAO userAccessLogDAO;

    public UserAccessLog saveUserLog(UserAccessLog userAccessLog) throws Exception{
        return userAccessLogDAO.saveAndFlush(userAccessLog);
    }
}
