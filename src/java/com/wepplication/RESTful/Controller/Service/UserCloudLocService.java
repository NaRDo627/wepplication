package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.UserCloudLoc;
import com.wepplication.RESTful.Domain.Users;

public interface UserCloudLocService {
    UserCloudLoc findUserCloudLocByLno(Integer lno) throws Exception;
}
