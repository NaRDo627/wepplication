package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userLogDAO")
public interface UserLogDAO extends JpaRepository<Users, Integer> {

}
