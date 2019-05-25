package com.wepplication.Controller.RESTful.Service.Impl;

import com.google.gson.JsonObject;
import com.wepplication.Controller.RESTful.Service.UserAccessLogService;
import com.wepplication.Domain.UserAccessLog;
import com.wepplication.Repository.UserAccessLogDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("userLogService")
public class UserAccessLogServiceImpl implements UserAccessLogService {
    // DAO
    @Resource(name = "userAccessLogDAO")
    UserAccessLogDAO userAccessLogDAO;

    public UserAccessLog saveUserLog(UserAccessLog userAccessLog) throws Exception{
        return userAccessLogDAO.saveAndFlush(userAccessLog);
    }

    public List<UserAccessLog> findUserAccessLogByUno(Integer uno) throws Exception {
        return userAccessLogDAO.findAllByUno(uno);
    }

    public JsonObject countUserAccessLogByUno(Integer uno) throws Exception {
        JsonObject retObj = new JsonObject();
        List<UserAccessLog> userAccessLogList = userAccessLogDAO.findAllByUno(uno);
        Integer imageEditorCount = 0;
        Integer videoEditorCount = 0;
        Integer gifEditorCount = 0;
        Integer encryptionCount = 0;
        Integer sshCount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm");

        for(UserAccessLog accessLog : userAccessLogList) {
            String reqUrl = accessLog.getReqUrl();
            // 지난 6개월만 추출

            String timeFormat;
            switch (reqUrl) {
                case "/editor/image_editor":
                    imageEditorCount++;
                    break;
                case "/editor/video_editor":
                    videoEditorCount++;
                    break;
                case "/editor/gif_editor":
                    gifEditorCount++;
                    break;
                case "/crypto/encryptWord":
                case "/crypto/encryptFile":
                    encryptionCount++;
                    break;
                case "/viewer/ssh_terminal":
                    sshCount++;
                    break;
                    default:
            }
        }

        return retObj;
    }
}
