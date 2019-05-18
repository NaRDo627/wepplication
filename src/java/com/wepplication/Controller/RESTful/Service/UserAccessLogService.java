package com.wepplication.Controller.RESTful.Service;

import com.wepplication.Domain.UserAccessLog;

public interface UserAccessLogService {
    public UserAccessLog saveUserLog(UserAccessLog userAccessLog) throws Exception;
}
