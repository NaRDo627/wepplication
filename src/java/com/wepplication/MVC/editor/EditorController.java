package com.wepplication.MVC.editor;

import com.wepplication.Util.FileUtil;
import com.wepplication.Util.LogUtil;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/editor")
public class EditorController {
    private static final String filePath = "./tmp/";

    @RequestMapping(value = {"/image_editor"}, method = RequestMethod.GET)
    public String editorImageEditorGet(Model model, HttpSession session){

        // 히스토리, 로그 업데이트
        if(session.getAttribute("users") != null) {
            JSONObject userObj = (JSONObject)session.getAttribute("users");
            Thread logThread = new Thread(() ->
                LogUtil.writeAllActivityLog(userObj, "/editor/image_editor", "이미지 편집"));
            logThread.start();
        }

        return "/editor/image_editor";
    }

    @RequestMapping(value = {"/video_editor"}, method = RequestMethod.GET)
    public String editorVideoEditorGet(Model model, HttpSession session){

        // 히스토리, 로그 업데이트
        if(session.getAttribute("users") != null) {
            JSONObject userObj = (JSONObject)session.getAttribute("users");
            Thread logThread = new Thread(() ->
                    LogUtil.writeAllActivityLog(userObj, "/editor/video_editor", "동영상 편집"));
            logThread.start();
        }

        return "/editor/video_editor";
    }

    @RequestMapping(value = {"/gif_editor"}, method = RequestMethod.GET)
    public String editorGifEditorGet(Model model, HttpSession session){

        // 히스토리, 로그 업데이트
        if(session.getAttribute("users") != null) {
            JSONObject userObj = (JSONObject)session.getAttribute("users");
            Thread logThread = new Thread(() ->
                    LogUtil.writeAllActivityLog(userObj, "/editor/gif_editor", "움짤 편집"));
            logThread.start();
        }

        return "/editor/gif_editor";
    }

    /* API */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public void fileUpload(HttpServletResponse response, MultipartFile upfile) {
        if(upfile==null) return;

        try {
            FileUtil fu = new FileUtil();
            String filename = fu.getNewName();
            fu.saveFile(upfile, filePath, filename );
            response.getWriter().print(filename);
        } catch (Exception ex) {
            System.out.println("File Upload Error");
        }
    }

    @RequestMapping(value = "/fileDownload")
    public void fileDownload(HttpServletResponse response, String filename) {
        FileUtil fu = new FileUtil();
        fu.fileDownload(response, filePath, filename );
    }

    @RequestMapping(value = "/imageDownload")
    public void imageDownload(HttpServletRequest request,
                              HttpServletResponse response, String filename) {
        FileUtil fu = new FileUtil();
        fu.imageDownload(request, response, filePath, filename );
    }

    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public void saveImage(HttpServletRequest request, MultipartFile imageFile) {
        String filename = filePath +"/"+ request.getParameter("filename");

        try {
            byte[] Filebyte = imageFile.getBytes();
            imageFile.getInputStream().read(Filebyte);

            String encString = new String(Filebyte, "ASCII");
            encString = encString.replace("data:image/png;base64,", "");

            FileOutputStream fs = new FileOutputStream(filename);
            DataOutputStream ds = new DataOutputStream(fs);

            byte[] data = Base64Utils.decodeFromString(encString);

            for (byte dataB : data) {
                ds.writeByte(dataB);
            }
            ds.close();
            fs.close();
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }
}