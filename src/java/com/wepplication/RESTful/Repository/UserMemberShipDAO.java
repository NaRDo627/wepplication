package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.UserMemberShip;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userMemberShipDAO")
public interface UserMemberShipDAO extends JpaRepository<UserMemberShip, Integer> {

}
