package com.wepplication.Controller.RESTful.Service.Impl;

import com.wepplication.Controller.RESTful.Service.UserMemberShipService;
import com.wepplication.Domain.UserMemberShip;
import com.wepplication.Repository.UserMemberShipDAO;
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
