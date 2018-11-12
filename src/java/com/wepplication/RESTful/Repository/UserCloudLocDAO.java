package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.UserCloudLoc;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userCloudLocDAO")
public interface UserCloudLocDAO extends JpaRepository<UserCloudLoc, Integer> {

}
