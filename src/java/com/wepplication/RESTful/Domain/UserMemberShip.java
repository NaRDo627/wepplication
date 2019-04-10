package com.wepplication.RESTful.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "user_membership")
public class UserMemberShip implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer umno;

    @Column(name = "uno")
    private Integer uno;

    @ManyToOne
    @JoinColumn(name = "mno", referencedColumnName = "mno")
    private MemberShip mno;

    @Column(name="start_time", columnDefinition = "DATETIME")
    private Timestamp startTime;

    @Column(name="end_time", columnDefinition = "DATETIME")
    private Timestamp endTime;

    @Column(name="is_auto_subscribe")
    private Integer isAutoSubscribe;

    @Column(name="insert_time", columnDefinition = "DATETIME")
    private Timestamp insertTime;

    @Column(name="update_time", columnDefinition = "DATETIME")
    private Timestamp updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUmno() {
        return umno;
    }

    public void setUmno(Integer umno) {
        this.umno = umno;
    }

    public Integer getUno() {
        return uno;
    }

    public void setUno(Integer uno) {
        this.uno = uno;
    }

    public MemberShip getMno() {
        return mno;
    }

    public void setMno(MemberShip mno) {
        this.mno = mno;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getIsAutoSubscribe() {
        return isAutoSubscribe;
    }

    public void setIsAutoSubscribe(Integer isAutoSubscribe) {
        this.isAutoSubscribe = isAutoSubscribe;
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
}
