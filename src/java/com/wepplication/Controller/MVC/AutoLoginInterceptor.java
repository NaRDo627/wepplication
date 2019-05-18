package com.wepplication.Controller.MVC;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wepplication.Domain.Users;
import com.wepplication.Util.EncryptUtil;
import com.wepplication.Util.RestUtil;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {
    private static final String API_ADDRESS = "http://localhost";
    private static final String API_PORT = "8081";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if(loginCookie != null && session.getAttribute("users") == null) {
            // TODO : 로그인 처리
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            JsonObject obj = (JsonObject) RestUtil.requestGet(addr + "/users/get_session/"+loginCookie.getValue(), headers);
            if (obj.has("res_code") && obj.get("res_code").getAsInt() == HttpURLConnection.HTTP_OK){
                JsonObject result = obj.get("result").getAsJsonObject();

                // gson에서 timestamp 파싱이 안돼서 삭제 후 파싱 후 넣음. 정말 대단해!
                Timestamp insertTime = new Timestamp(result.get("insertTime").getAsLong());
                Timestamp updateTime = new Timestamp(result.get("updateTime").getAsLong());
                Timestamp sessionTimeUntil = null;
                if(!result.get("sessionTimeUntil").isJsonNull())
                    sessionTimeUntil = new Timestamp(result.get("sessionTimeUntil").getAsLong());

                result.remove("insertTime");
                result.remove("updateTime");
                result.remove("sessionTimeUntil");

                Gson gson = new Gson();
                Users users = gson.fromJson(result, Users.class);
                users.setInsertTime(insertTime);
                users.setUpdateTime(updateTime);
                users.setSessionTimeUntil(sessionTimeUntil);
                session.setAttribute("users", users);
            }
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
