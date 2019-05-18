package com.wepplication.Repository;

import com.wepplication.Domain.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("memberShipDAO")
public interface MemberShipDAO extends JpaRepository<MemberShip, Integer> {

}
