package com.wepplication.Controller.RESTful.Service;

import com.wepplication.Domain.UserMemberShip;

import java.util.List;

public interface UserMemberShipService {
    public List<UserMemberShip> findUserMemberShipAll() throws Exception;
    public UserMemberShip findUserMemberShipByUmno(Integer umno) throws Exception;
    public UserMemberShip saveUserMemberShip(UserMemberShip userMemberShip) throws Exception;
}
