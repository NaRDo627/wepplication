package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Exception.UnauthorizedException;
import com.wepplication.RESTful.Repository.UsersDAO;
import com.wepplication.Util.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.List;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
    // DAO
    @Resource(name = "usersDAO")
    UsersDAO usersDAO;

    public List<Users> findUsersAll() throws Exception {
        return usersDAO.findAll();
    }

    public Users findUsersByUno(Integer uno) throws Exception {
        return usersDAO.findOne(uno);
    }

    public Integer countUsersByUserId(String id) throws Exception {
        return usersDAO.countAllByUserId(id);
    }

    public Integer countUsersByEmail(String email) throws Exception {
        return usersDAO.countAllByEmail(email);
    }

    // 가입
    public Users saveUser(Users users) throws Exception {
        return usersDAO.saveAndFlush(users);
    }

    // 인증 & 개인정보 조회
    public Users authentication(String token) throws Exception {
// authorization으로부터 type과 credential을 분리
        String[] split = token.split(" ");
        String type = split[0];
        String credential = split[1];

        Users user = null;

        if ("Basic".equalsIgnoreCase(type)) {
            // credential을 디코딩하여 username과 password를 분리
            String decoded = new String(Base64Utils.decodeFromString(credential));
            String[] usernameAndPassword = decoded.split(":");

            user = usersDAO.findByUserIdAndPassword(usernameAndPassword[0], usernameAndPassword[1]);
        }
        if(user == null)
            throw new UnauthorizedException("User Unexists");

        return user;
    }

    // 비밀번호 변경
    public Users updatePassword(String token, String password) throws Exception {
        Users user = this.authentication(token);
        user.setPassword(password);
        return usersDAO.saveAndFlush(user);
    }

    // 탈퇴
    public void withdraw(String token) throws Exception {
        Users user = this.authentication(token);
        usersDAO.delete(user);
    }
}
