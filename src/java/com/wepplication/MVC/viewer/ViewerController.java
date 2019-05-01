package com.wepplication.MVC.viewer;

import com.wepplication.Util.LogUtil;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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
    public String sshTerminalGet(Model model, HttpSession session){

        // 히스토리, 로그 업데이트
        if(session.getAttribute("users") != null) {
            JSONObject userObj = (JSONObject)session.getAttribute("users");
            Thread logThread = new Thread(() ->
                    LogUtil.writeAllActivityLog(userObj, "/viewer/ssh_terminal", "SSH 터미널"));
            logThread.start();
        }

        return "/viewer/ssh_terminal";
    }
}