package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.MemberShipService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.MemberShip;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Repository.MemberShipDAO;
import com.wepplication.RESTful.Repository.UsersDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberShipService")
public class MemberShipServiceImpl implements MemberShipService {
    // DAO
    @Resource(name = "memberShipDAO")
    MemberShipDAO memberShipDAO;

    public MemberShip findMemberShipByMno(Integer mno) throws Exception {
        return memberShipDAO.findOne(mno);
    }
}