package com.wepplication.Controller.RESTful.Service;

import com.google.gson.JsonObject;
import com.wepplication.Domain.UserAccessLog;

import java.util.List;

public interface UserAccessLogService {
    public List<UserAccessLog> findUserAccessLogByUno(Integer uno) throws Exception;
    public JsonObject countUserAccessLogByUno(Integer uno) throws Exception;
    public UserAccessLog saveUserLog(UserAccessLog userAccessLog) throws Exception;

}
