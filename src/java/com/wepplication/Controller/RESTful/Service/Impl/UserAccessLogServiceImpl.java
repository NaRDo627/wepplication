package com.wepplication.Controller.RESTful.Service.Impl;

import com.wepplication.Controller.RESTful.Service.UserAccessLogService;
import com.wepplication.Domain.UserAccessLog;
import com.wepplication.Repository.UserAccessLogDAO;
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
