package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userLogDAO")
public interface UserAccessLogDAO extends JpaRepository<UserAccessLog, Integer> {

}
