package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.CloudFileService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.CloudFile;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cloud")
public class CloudFileController {

    @Resource(name = "cloudFileService")
    private CloudFileService cloudFileService;

    @RequestMapping("/{fno}")
    public CloudFile cloudFileGet(@PathVariable(value="fno") Integer fno) {
        System.out.println(fno);
        try{
            return cloudFileService.findCloudFileByfno(fno);
        } catch (Exception e) {
            return null;
        }
    }
}
