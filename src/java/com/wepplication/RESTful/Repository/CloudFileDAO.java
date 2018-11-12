package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.CloudFile;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cloudFileDAO")
public interface CloudFileDAO extends JpaRepository<CloudFile, Integer> {

}
