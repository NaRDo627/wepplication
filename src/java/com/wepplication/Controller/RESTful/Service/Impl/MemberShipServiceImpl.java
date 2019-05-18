package com.wepplication.Controller.RESTful.Service.Impl;

import com.wepplication.Controller.RESTful.Service.MemberShipService;
import com.wepplication.Domain.MemberShip;
import com.wepplication.Repository.MemberShipDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("memberShipService")
public class MemberShipServiceImpl implements MemberShipService {
    // DAO
    @Resource(name = "memberShipDAO")
    MemberShipDAO memberShipDAO;

    public List<MemberShip> findMemberShipAll() throws Exception {
        return memberShipDAO.findAll();
    }

    public MemberShip findMemberShipByMno(Integer mno) throws Exception {
        return memberShipDAO.findOne(mno);
    }

    public MemberShip saveMemberShip(MemberShip memberShip) throws Exception {
        return memberShipDAO.saveAndFlush(memberShip);
    }
}
