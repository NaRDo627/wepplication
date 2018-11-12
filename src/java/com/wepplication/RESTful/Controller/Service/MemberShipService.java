package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.MemberShip;
import com.wepplication.RESTful.Domain.Users;

public interface MemberShipService {
    MemberShip findMemberShipByMno(Integer mno) throws Exception;
}
