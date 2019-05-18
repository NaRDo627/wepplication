package com.wepplication.Util;

import com.google.gson.*;
import com.wepplication.Domain.UserAccessLog;
import com.wepplication.Domain.Users;
import org.codehaus.jettison.json.JSONObject;

import java.sql.Timestamp;
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
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Timestamp.class, (JsonDeserializer<Timestamp>) (json, typeOfT, context) -> new Timestamp(json.getAsJsonPrimitive().getAsLong()))
                    .registerTypeAdapter(Timestamp.class, (JsonSerializer<Timestamp>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                    .create();

            user.setLastUsedUrl(url);
            user.setLastUsed(description);

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            RestUtil.requestPut(apiAddress + "/users", headers, gson.toJson(user));
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }

    static public void writeUserLog(Users user, String url, String description) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Timestamp.class, (JsonDeserializer<Timestamp>) (json, typeOfT, context) -> new Timestamp(json.getAsJsonPrimitive().getAsLong()))
                    .registerTypeAdapter(Timestamp.class, (JsonSerializer<Timestamp>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                    .create();

            UserAccessLog userLog = new UserAccessLog();
            userLog.setUno(user.getUno());
            userLog.setReqUrl(url);
            userLog.setDescription(description);

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            RestUtil.requestPost(apiAddress + "/userlog", headers, gson.toJson(userLog));
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }
}
