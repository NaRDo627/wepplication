package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.UserMemberShipService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.UserMemberShip;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Repository.UserMemberShipDAO;
import com.wepplication.RESTful.Repository.UsersDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userMemberShipService")
public class UserMemberShipServiceImpl implements UserMemberShipService {
    // DAO
    @Resource(name = "userMemberShipDAO")
    UserMemberShipDAO userMemberShipDAO;

    public List<UserMemberShip> findUserMemberShipAll() throws Exception {
        return userMemberShipDAO.findAll();
    }

    public UserMemberShip findUserMemberShipByUmno(Integer umno) throws Exception {
        return userMemberShipDAO.findOne(umno);
    }

    public UserMemberShip saveUserMemberShip(UserMemberShip userMemberShip) throws Exception {
        return userMemberShipDAO.saveAndFlush(userMemberShip);
    }
}
