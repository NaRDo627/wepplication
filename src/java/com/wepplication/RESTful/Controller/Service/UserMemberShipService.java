package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.UserMemberShip;
import com.wepplication.RESTful.Domain.Users;

import java.util.List;

public interface UserMemberShipService {
    public List<UserMemberShip> findUserMemberShipAll() throws Exception;
    public UserMemberShip findUserMemberShipByUmno(Integer umno) throws Exception;
    public UserMemberShip saveUserMemberShip(UserMemberShip userMemberShip) throws Exception;
}
