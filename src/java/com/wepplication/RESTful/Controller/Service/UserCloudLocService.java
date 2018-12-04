package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.UserCloudLoc;
import com.wepplication.RESTful.Domain.Users;

import java.util.List;

public interface UserCloudLocService {
    public List<UserCloudLoc> findUserCloudLocAll() throws Exception;
    public UserCloudLoc findUserCloudLocByLno(Integer lno) throws Exception;
}
