package com.wepplication.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "user_access_log")
public class UserAccessLog implements Serializable, Comparable<UserAccessLog> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ulno;

    @Column(name = "uno")
    private Integer uno;

    @Column(name="req_url", columnDefinition = "NVARCHAR(255)")
    private String reqUrl;

    @Column(name="req_time", columnDefinition = "DATETIME")
    private Timestamp reqTime;

    @Column(name="description", columnDefinition = "NVARCHAR(512)")
    private String description;

    public Integer getUno() {
        return uno;
    }

    public Integer getUlno() {
        return ulno;
    }

    public void setUlno(Integer ulno) {
        this.ulno = ulno;
    }

    public void setUno(Integer uno) {
        this.uno = uno;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public Timestamp getReqTime() {
        return reqTime;
    }

    public void setReqTime(Timestamp reqTime) {
        this.reqTime = reqTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(UserAccessLog userAccessLog) {
        if(this.reqTime.getTime() < userAccessLog.getReqTime().getTime()){
            return 1;
        } else if(this.reqTime.getTime() > userAccessLog.getReqTime().getTime()){
            return -1;
        }
        return 0;
    }
}
