package com.wepplication.Util;

import com.wepplication.Domain.Users;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogUtil {
    private static final String API_ADDRESS = "http://localhost";
    private static final String API_PORT = "8081";

    static public void writeAllActivityLog(Users user, String url, String description) {
        writeUserActivity(user, url, description);
        writeUserLog(user, url, description);
    }

    static public void writeUserActivity(Users user, String url, String description) {
        try {
            user.setLastUsedUrl("lastUserUrl");
            user.setLastUsedUrl("lastUsed");

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            RestUtil.requestPut(apiAddress + "/users", headers, user);
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }

    static public void writeUserLog(Users user, String url, String description) {
        try {
            JSONObject userLogObj = new JSONObject();
            userLogObj.put("uno", user.getUno());
            userLogObj.put("reqUrl", url);
            userLogObj.put("description", description);

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            RestUtil.requestPost(apiAddress + "/userlog", headers, userLogObj);
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }
}
