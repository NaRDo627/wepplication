package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.CloudFileService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.CloudFile;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Repository.CloudFileDAO;
import com.wepplication.RESTful.Repository.UsersDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cloudFileService")
public class CloudFileServiceImpl implements CloudFileService {
    // DAO
    @Resource(name = "cloudFileDAO")
    CloudFileDAO cloudFileDAO;

    public List<CloudFile> findCloudFileAll() throws Exception {
        return cloudFileDAO.findAll();
    }

    public CloudFile findCloudFileByfno(Integer fno) throws Exception {
        return cloudFileDAO.findOne(fno);
    }
}
