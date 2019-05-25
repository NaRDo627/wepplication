package com.wepplication.Controller.RESTful.Service.Impl;

import com.google.api.client.json.Json;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wepplication.Controller.RESTful.Service.UserAccessLogService;
import com.wepplication.Domain.UserAccessLog;
import com.wepplication.Form.MorrisLogForm;
import com.wepplication.Repository.UserAccessLogDAO;
import com.wepplication.Util.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("userLogService")
public class UserAccessLogServiceImpl implements UserAccessLogService {
    // DAO
    @Resource(name = "userAccessLogDAO")
    UserAccessLogDAO userAccessLogDAO;

    Gson gson = new Gson();

    public UserAccessLog saveUserLog(UserAccessLog userAccessLog) throws Exception{
        return userAccessLogDAO.saveAndFlush(userAccessLog);
    }

    public List<UserAccessLog> findUserAccessLogByUno(Integer uno) throws Exception {
        return userAccessLogDAO.findAllByUno(uno);
    }

    public List<MorrisLogForm> countUserAccessLogByUno(Integer uno) throws Exception {
        JsonObject retObj = new JsonObject();
        //JsonArray retAry = new JsonArray();
        List<MorrisLogForm> formList = new ArrayList<>();
        List<UserAccessLog> userAccessLogList = userAccessLogDAO.findAllByUno(uno);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp now = DateTimeUtil.now();
        Collections.sort(userAccessLogList);

        MorrisLogForm data = new MorrisLogForm();
        for(UserAccessLog accessLog : userAccessLogList) {
            String reqUrl = accessLog.getReqUrl();
            // 지난 6개월만 추출

            Timestamp date = accessLog.getReqTime();
            Long diff = now.getTime() - date.getTime();
            if(diff / 1000 > 60 * 60 * 24 * 30 * 6)
                break;

            // Step 1. 처음 실행되는 거라면 객체 생성, 아니라면 이전값과 비교하여 바뀌면 생성
            String timeFormat = dateFormat.format(date);
            if(data.getTime() == null){
                data.setTime(timeFormat);
                data.setImageEditor(0);
                data.setVideoEditor(0);
                data.setGifEditor(0);
                data.setEncryption(0);
                data.setSsh(0);
            } else if(!data.getTime().equals(timeFormat)) {
                formList.add(data);
                //retAry.add(gson.toJson(data));
                data = new MorrisLogForm();
                data.setTime(timeFormat);
                data.setImageEditor(0);
                data.setVideoEditor(0);
                data.setGifEditor(0);
                data.setEncryption(0);
                data.setSsh(0);
            }

            // Step 2. 각 요청 URL마다 조회 횟수 갱신
            Integer count = 0;
            switch (reqUrl) {
                case "/editor/image_editor":
                    count = data.getImageEditor();
                    data.setImageEditor(++count);
                    break;
                case "/editor/video_editor":
                    count = data.getVideoEditor();
                    data.setVideoEditor(++count);
                    break;
                case "/editor/gif_editor":
                    count = data.getGifEditor();
                    data.setGifEditor(++count);
                    break;
                case "/crypto/encryptWord":
                case "/crypto/encryptFile":
                    count = data.getEncryption();
                    data.setEncryption(++count);
                    break;
                case "/viewer/ssh_terminal":
                    count = data.getSsh();
                    data.setSsh(++count);
                    break;
                    default:
            }
        }
        //retAry.add(gson.toJson(data));
        formList.add(data);
        //retObj.add("result", retAry);

        return formList;
    }
}
