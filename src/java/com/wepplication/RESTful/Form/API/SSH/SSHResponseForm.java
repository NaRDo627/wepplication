package com.wepplication.RESTful.Form.API.SSH;

import com.wepplication.RESTful.Domain.Users;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


public class SSHResponseForm {
    private String status;
    private String Response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
