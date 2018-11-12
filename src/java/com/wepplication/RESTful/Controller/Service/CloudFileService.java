package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.CloudFile;
import com.wepplication.RESTful.Domain.Users;

public interface CloudFileService {
    CloudFile findCloudFileByfno(Integer fno) throws Exception;
}
