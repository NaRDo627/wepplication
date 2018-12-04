package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.Users;

import java.util.List;

public interface UsersService {
    public List<Users> findUsersAll() throws Exception;
    public Users findUsersByUno(Integer uno) throws Exception;

    // 가입
    public Users saveUser(Users users);

    // 인증 & 개인정보 조회
    public Users authentication(String token);

    // 비밀번호 변경
    public Users updatePassword(String token, String password);

    // 탈퇴
    public void withdraw(String token);
}
