package com.wepplication.Controller.RESTful.Service;

import com.wepplication.Domain.MemberShip;

import java.util.List;

public interface MemberShipService {
    public List<MemberShip> findMemberShipAll() throws Exception;
    public MemberShip findMemberShipByMno(Integer mno) throws Exception;
    public MemberShip saveMemberShip(MemberShip memberShip) throws Exception;
}
