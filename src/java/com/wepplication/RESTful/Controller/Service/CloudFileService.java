package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.CloudFile;
import com.wepplication.RESTful.Domain.Users;

import java.util.List;

public interface CloudFileService {
    public List<CloudFile> findCloudFileAll() throws Exception;
    public CloudFile findCloudFileByfno(Integer fno) throws Exception;
}
