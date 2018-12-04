package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.CloudFileService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.CloudFile;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cloud_file")
public class CloudFileController {

    @Resource(name = "cloudFileService")
    private CloudFileService cloudFileService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<CloudFile> cloudFileGet() {
        System.out.println("select cloud_file *");
        try{
            return cloudFileService.findCloudFileAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = {"/{fno}"}, method = RequestMethod.GET)
    public CloudFile cloudFileGet(@PathVariable(value="fno") Integer fno) {
        System.out.println("select cloud_file where fno=" + fno);
        try{
            return cloudFileService.findCloudFileByfno(fno);
        } catch (Exception e) {
            return null;
        }
    }
}
