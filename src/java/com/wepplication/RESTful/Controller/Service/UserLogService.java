package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.UserLog;
import com.wepplication.RESTful.Domain.Users;

import java.util.List;

public interface UserLogService {
    public UserLog saveUserLog(UserLog userLog) throws Exception;
}
