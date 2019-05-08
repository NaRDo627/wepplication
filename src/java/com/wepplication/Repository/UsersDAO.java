package com.wepplication.Repository;

import com.wepplication.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("usersDAO")
public interface UsersDAO extends JpaRepository<Users, Integer> {
    Users findByUserIdAndPassword(String userId, String password);
    Integer countAllByUserId(String userId);
    Integer countAllByEmail(String email);
}
