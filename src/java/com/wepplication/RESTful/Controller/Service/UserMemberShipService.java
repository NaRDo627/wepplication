package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.UserMemberShip;
import com.wepplication.RESTful.Domain.Users;

public interface UserMemberShipService {
    UserMemberShip findMemberShipByUmno(Integer umno) throws Exception;
}
