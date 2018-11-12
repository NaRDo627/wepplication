package com.wepplication.RESTful.Domain;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "cloud_file")
public class CloudFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fno;

    @ManyToOne
    @JoinColumn(name = "uno", referencedColumnName = "uno")
    private Users uno;

    @Nationalized
    @Column(name="file_name", columnDefinition = "NVARCHAR(255)")
    private String fileName;

    @Column(name="file_size")
    private Integer fileSize;

    @Nationalized
    @Column(name="location", columnDefinition = "NVARCHAR(255)")
    private String location;

    @Column(name="insert_time", columnDefinition = "DATETIME")
    private Timestamp insertTime;

    @Column(name="update_time", columnDefinition = "DATETIME")
    private Timestamp updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getFno() {
        return fno;
    }

    public void setFno(Integer fno) {
        this.fno = fno;
    }

    public Users getUno() {
        return uno;
    }

    public void setUno(Users uno) {
        this.uno = uno;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
