package com.wepplication.RESTful.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "payment_info")
public class PaymentInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pno;

    @ManyToOne
    @JoinColumn(name = "uno", referencedColumnName = "uno")
    private Users uno;

    @ManyToOne
    @JoinColumn(name = "mno", referencedColumnName = "mno")
    private MemberShip mno;

    @Column(name="method")
    private Integer method;

    @Column(name="amount")
    private Integer amount;

    @Column(name="insert_time", columnDefinition = "DATETIME")
    private Timestamp insertTime;

    @Column(name="update_time", columnDefinition = "DATETIME")
    private Timestamp updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    public Users getUno() {
        return uno;
    }

    public void setUno(Users uno) {
        this.uno = uno;
    }

    public MemberShip getMno() {
        return mno;
    }

    public void setMno(MemberShip mno) {
        this.mno = mno;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
