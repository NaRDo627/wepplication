package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("usersDAO")
public interface UsersDAO extends JpaRepository<Users, Integer> {

}
