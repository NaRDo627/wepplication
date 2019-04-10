package com.wepplication.Util;

import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogUtil {
    private static final String API_ADDRESS = "http://localhost";
    private static final String API_PORT = "8081";

    // TODO 스레드 구현
    static public void logActivity(JSONObject user, String url, String description) {
        try {
            user.put("lastUsed", url);
            JSONObject userLogObj = new JSONObject();
            userLogObj.put("uno", (int)user.get("uno"));
            userLogObj.put("reqUrl", url);
            userLogObj.put("description", description);

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            RestUtil.requestPut(apiAddress + "/users", headers, user);
            RestUtil.requestPost(apiAddress + "/userlog", headers, userLogObj);
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }
}
