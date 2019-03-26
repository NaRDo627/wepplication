package com.wepplication.MVC.viewer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/viewer")
public class ViewerController {

    @RequestMapping(value = {"/image_viewer"}, method = RequestMethod.GET)
    public String imageViewerGet(){
        return "/viewer/image_viewer";
    }

    @RequestMapping(value = {"/office_viewer"}, method = RequestMethod.GET)
    public String officeViewerGet(){
        return "/viewer/office_viewer";
    }

    @RequestMapping(value = {"/video_viewer"}, method = RequestMethod.GET)
    public String videoViewerGet(){
        return "/viewer/video_viewer";
    }

    @RequestMapping(value = {"/ssh_terminal"}, method = RequestMethod.GET)
    public String sshTerminalGet(){
        return "/viewer/ssh_terminal";
    }
}