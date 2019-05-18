package com.wepplication.Controller.MVC;

import com.google.gson.*;
import com.wepplication.Domain.MemberShip;
import com.wepplication.Domain.UserMemberShip;
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

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Timestamp.class, (JsonDeserializer<Timestamp>) (json, typeOfT, context) -> new Timestamp(json.getAsJsonPrimitive().getAsLong()))
                        .registerTypeAdapter(Timestamp.class, (JsonSerializer<Timestamp>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                        .create();
                Users users = gson.fromJson(result, Users.class);
                session.setAttribute("users", users);

                // [190410][HKPARK] user_membership 레코드가 없으면 업데이트
                obj = (JsonObject) RestUtil.requestGet(addr + "/user_membership/" + ((JsonObject)obj.get("result")).get("uno").getAsInt(), headers);

                UserMemberShip userMemberShip = null;
                if(obj.get("res_code").getAsInt() == HttpURLConnection.HTTP_NOT_FOUND) {
                    userMemberShip = new UserMemberShip();
                    userMemberShip.setUno(users.getUno());
                    userMemberShip.setMno(1);
                    userMemberShip.setIsAutoSubscribe(0);
                    result = ((JsonObject)RestUtil.requestPost(addr + "/user_membership/", headers, gson.toJson(userMemberShip))).
                            getAsJsonObject("result");

                    userMemberShip = gson.fromJson(result, UserMemberShip.class);
                } else{
                    result = obj.getAsJsonObject("result");
                    userMemberShip = gson.fromJson(result, UserMemberShip.class);
                }

                session.setAttribute("user_membership", userMemberShip);
            }
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
