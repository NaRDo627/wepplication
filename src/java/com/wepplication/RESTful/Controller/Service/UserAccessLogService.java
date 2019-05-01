package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.UserAccessLog;

public interface UserAccessLogService {
    public UserAccessLog saveUserLog(UserAccessLog userAccessLog) throws Exception;
}
