package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.MemberShip;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("memberShipDAO")
public interface MemberShipDAO extends JpaRepository<MemberShip, Integer> {

}
