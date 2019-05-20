package com.wepplication.Controller.RESTful.Service;

import com.wepplication.Domain.Users;

import java.sql.Timestamp;
import java.util.List;

public interface UsersService {
    public List<Users> findUsersAll() throws Exception;
    public Users findUsersByUno(Integer uno) throws Exception;
    public Users findUsersBySessionKey(String sessionKey) throws Exception;
    public Users findUsersByUserNameAndEmail(String userName, String email) throws Exception;
    public Users findUsersByUserIdAndEmail(String userId, String email) throws Exception;
    public Integer countUsersByUserId(String id) throws Exception;
    public Integer countUsersByEmail(String email) throws Exception;

    // 가입
    public Users saveUser(Users users) throws Exception;
    public Users saveUserAutoLogin(Users users, String sessionKey, Timestamp sessionTimeUntil) throws Exception;

    // 인증 & 개인정보 조회
    public Users authentication(String token) throws Exception;

    // 비밀번호 변경
    public Users updatePassword(String token, String password) throws Exception;

    // 탈퇴
    public void withdraw(String token) throws Exception;
}
