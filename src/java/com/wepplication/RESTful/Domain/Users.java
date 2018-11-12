package com.wepplication.RESTful.Domain;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uno;

    @Nationalized
    @Column(name="user_id", columnDefinition = "NVARCHAR(50)")
    private String userId;

    @Nationalized
    @Column(name="password", columnDefinition = "NVARCHAR(50)")
    private String password;

    @Nationalized
    @Column(name="user_name", columnDefinition = "NVARCHAR(50)")
    private String userName;

    @Nationalized
    @Column(name="user_nickname", columnDefinition = "NVARCHAR(50)")
    private String userNickname;

    @Nationalized
    @Column(name="email", columnDefinition = "NVARCHAR(50)")
    private String email;

    @Nationalized
    @Column(name="last_used", columnDefinition = "NVARCHAR(50)")
    private String lastUsed;

    @Column(name="membership_status")
    private Integer memberShipStatus;

    @Column(name="insert_time", columnDefinition = "DATETIME")
    private Timestamp insertTime;

    @Column(name="update_time", columnDefinition = "DATETIME")
    private Timestamp updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUno() {
        return uno;
    }

    public void setUno(Integer uno) {
        this.uno = uno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMemberShipStatus() {
        return memberShipStatus;
    }

    public void setMemberShipStatus(Integer memberShipStatus) {
        this.memberShipStatus = memberShipStatus;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(String lastUsed) {
        this.lastUsed = lastUsed;
    }
}