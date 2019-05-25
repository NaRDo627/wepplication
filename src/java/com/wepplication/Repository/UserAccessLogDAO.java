package com.wepplication.Repository;

import com.wepplication.Domain.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userAccessLogDAO")
public interface UserAccessLogDAO extends JpaRepository<UserAccessLog, Integer> {
    List<UserAccessLog> findAllByUno(Integer uno);
}
