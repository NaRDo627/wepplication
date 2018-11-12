package com.wepplication.RESTful.Domain;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_cloud_loc")
public class UserCloudLoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lno;

    @ManyToOne
    @JoinColumn(name = "uno", referencedColumnName = "uno")
    private Users uno;

    @Nationalized
    @Column(name="url", columnDefinition = "NVARCHAR(255)")
    private String url;

    @Nationalized
    @Column(name="path", columnDefinition = "NVARCHAR(255)")
    private String path;

    @Column(name="allowed_size")
    private Integer allowedSize;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getLno() {
        return lno;
    }

    public void setLno(Integer lno) {
        this.lno = lno;
    }

    public Users getUno() {
        return uno;
    }

    public void setUno(Users uno) {
        this.uno = uno;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getAllowedSize() {
        return allowedSize;
    }

    public void setAllowedSize(Integer allowedSize) {
        this.allowedSize = allowedSize;
    }
}
