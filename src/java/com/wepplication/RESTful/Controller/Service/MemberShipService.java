package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.MemberShip;
import com.wepplication.RESTful.Domain.Users;

import java.util.List;

public interface MemberShipService {
    public List<MemberShip> findMemberShipAll() throws Exception;
    public MemberShip findMemberShipByMno(Integer mno) throws Exception;
    public MemberShip saveMemberShip(MemberShip memberShip) throws Exception;
}
