package com.wepplication.Repository;

import com.wepplication.Domain.UserMemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userMemberShipDAO")
public interface UserMemberShipDAO extends JpaRepository<UserMemberShip, Integer> {

}
