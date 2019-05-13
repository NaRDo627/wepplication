package com.wepplication.Controller.MVC;

import com.wepplication.Domain.MemberShip;
import com.wepplication.Domain.UserMemberShip;
import com.wepplication.Domain.Users;
import com.wepplication.Util.DateTimeUtil;
import com.wepplication.Util.EncryptUtil;
import com.wepplication.Util.RestUtil;
//import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.net.HttpURLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.Base64Utils;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {
    //private static final String API_ADDRESS = "http://wepplication.koreacentral.cloudapp.azure.com";
    private static final String API_ADDRESS = "http://localhost";
    private static final String API_PORT = "8081";
    private static final String SENDER_EMAIL_ADDR = "wepplication.do.not.reply@gmail.com";
    private static final String SENDER_EMAIL_PASS = "dnpqvmfflzpdltus";

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String indexGet(Model model, HttpSession session){
        return "index";
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.GET)
    public String loginGet(Model model, HttpSession session){
        return "login";
    }

    @RequestMapping(value = {"member"}, method = RequestMethod.GET)
    public String memberGet(Model model, HttpSession session){
        return "member";
    }

    @RequestMapping(value = {"profile"}, method = RequestMethod.GET)
    public String profileGet(Model model, HttpSession session){
        model.addAttribute("tab_select", "home-tab");
        return "profile";
    }

    @RequestMapping(value = {"subscribe"}, method = RequestMethod.GET)
    public String settingGet(Model model, HttpSession session){
        model.addAttribute("tab_select", "membership-tab");
        return "profile";
    }

    @RequestMapping(value = {"logout"}, method = RequestMethod.GET)
    public String logoutGet(Model model, HttpSession session){
        session.removeAttribute("users");
        session.removeAttribute("user_membership");
        session.removeAttribute("membership");

        return "login";
    }

    @RequestMapping(value = {"verify/{auth}"}, method = RequestMethod.GET)
    public String verifyGet(@PathVariable(value="auth") String authorization,
                            Model model, HttpSession session) {
        try{
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"authorization", "Basic " + authorization});
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            JsonObject obj = (JsonObject)RestUtil.requestPut(addr + "/users/verification", headers);

            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return "redirect:/";
            }

            if(session.getAttribute("users") != null) {
                try{
                    JsonObject users = (JsonObject)session.getAttribute("users");
                    session.removeAttribute("users");
                    users.addProperty("verfied", 1);
                    session.setAttribute("users", users);
                } catch (Exception e){
                    // do nothing for now
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "/";
        }

        return "verified";
    }

    /* api */
    @RequestMapping(value = {"login"}, method = RequestMethod.POST, produces = "application/text;charset=utf-8")
    public @ResponseBody
    String loginPost(@RequestParam("userId") String userId,
                      @RequestParam("password") String password,
                     @RequestParam("autoLogin") Integer autoLogin,
                      Model model, HttpSession session, HttpServletResponse response) {

        try{
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"authorization", "Basic " + Base64Utils.encodeToString((userId + ":" + EncryptUtil.encryptByMd5("whfwkrtlfjbb" + password)).getBytes())});
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            JsonObject obj = (JsonObject) RestUtil.requestGet(addr + "/users/myinfo", headers);
            if(obj.get("error").getAsBoolean())
                throw new Exception("서버 오류입니다. 나중에 다시 시도해 주세요.\n문제가 계속되면 관리자에게 문의 바랍니다.");

            if (obj.get("res_code").getAsInt() == HttpURLConnection.HTTP_UNAUTHORIZED){
                return "unauthorized";
            }else if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return obj.get("res_msg").getAsString();
            }

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

            Users users = gson.fromJson(result, Users.class);
            users.setInsertTime(insertTime);
            users.setUpdateTime(updateTime);
            users.setSessionTimeUntil(sessionTimeUntil);
            session.setAttribute("users", users);
            Integer uno = users.getUno();

            // [190410][HKPARK] user_membership 레코드가 없으면 업데이트
            obj = (JsonObject) RestUtil.requestGet(addr + "/user_membership/" + ((JsonObject)obj.get("result")).get("uno").getAsInt(), headers);

            UserMemberShip userMemberShip = null;
            if(obj.get("res_code").getAsInt() == HttpURLConnection.HTTP_NOT_FOUND) {
                userMemberShip = new UserMemberShip();
                userMemberShip.setUno(uno);
                userMemberShip.setMno(1);
                userMemberShip.setIsAutoSubscribe(0);
                result = ((JsonObject)RestUtil.requestPost(addr + "/user_membership/", headers, userMemberShip)).
                        get("result").getAsJsonObject();

                insertTime = new Timestamp(result.get("insertTime").getAsLong());
                updateTime = new Timestamp(result.get("updateTime").getAsLong());
                Timestamp startTime = new Timestamp(result.get("startTime").getAsLong());
                Timestamp endTime = new Timestamp(result.get("endTime").getAsLong());
                result.remove("insertTime");
                result.remove("updateTime");
                result.remove("startTime");
                result.remove("endTime");
                userMemberShip = gson.fromJson(result, UserMemberShip.class);
                userMemberShip.setInsertTime(insertTime);
                userMemberShip.setUpdateTime(updateTime);
                userMemberShip.setStartTime(startTime);
                userMemberShip.setEndTime(endTime);
            } else{
                result = obj.get("result").getAsJsonObject();
                insertTime = new Timestamp(result.get("insertTime").getAsLong());
                updateTime = new Timestamp(result.get("updateTime").getAsLong());
                Timestamp startTime = new Timestamp(result.get("startTime").getAsLong());
                Timestamp endTime = new Timestamp(result.get("endTime").getAsLong());
                result.remove("insertTime");
                result.remove("updateTime");
                result.remove("startTime");
                result.remove("endTime");
                userMemberShip = gson.fromJson(result, UserMemberShip.class);
                userMemberShip.setInsertTime(insertTime);
                userMemberShip.setUpdateTime(updateTime);
                userMemberShip.setStartTime(startTime);
                userMemberShip.setEndTime(endTime);
            }

            session.setAttribute("user_membership", userMemberShip);

            obj = (JsonObject) RestUtil.requestGet(addr + "/membership/" + userMemberShip.getMno(), headers);
            MemberShip membershipInfo = gson.fromJson(obj.get("result"), MemberShip.class);

            session.setAttribute("membership", membershipInfo);

            if(autoLogin == 1){
                Integer amount = 60 * 60 * 24 * 7; // 7 days
                sessionTimeUntil = new Timestamp(DateTimeUtil.now().getTime() + amount);
                users.setSessionKey(session.getId());
                users.setSessionTimeUntil(sessionTimeUntil);
                JsonObject sendUser = parser.parse(gson.toJson(users)).getAsJsonObject();
                sendUser.addProperty("insertTime", users.getInsertTime().getTime());
                sendUser.addProperty("updateTime", users.getUpdateTime().getTime());
                sendUser.addProperty("sessionTimeUntil", users.getSessionTimeUntil().getTime());
                RestUtil.requestPut(addr + "/users", headers, sendUser);
                Cookie loginCookie = new Cookie("loginCookie", session.getId());
                loginCookie.setPath("/");
                loginCookie.setMaxAge(amount);
                response.addCookie(loginCookie);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }

    @RequestMapping(value = {"member"}, method = RequestMethod.POST)
    public @ResponseBody
    String memberPost(@RequestBody Map<String, String> memberForm,
                      Model model, HttpSession session){

        try{
            String userId = memberForm.get("userId");

            String email = memberForm.get("email");
            String userName = memberForm.get("userName");

            // map -> object 변환
            JsonObject jsonObject = new JsonObject();
            for( Map.Entry<String, String> entry : memberForm.entrySet() ) {
                String key = entry.getKey();
                String value = entry.getValue();
                jsonObject.addProperty(key, value);
            }

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            JsonObject obj = (JsonObject)RestUtil.requestPost(addr + "/users", headers, jsonObject);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return obj.get("res_msg").getAsString();
            }

            String password = ((JsonObject)obj.get("result")).get("password").getAsString();

            // 이메일 중복 체크
            obj = (JsonObject)RestUtil.requestGet(addr + "/users/check_duplicate_email/" + email, headers);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return obj.get("res_msg").getAsString();
            }

            if(((JsonObject)obj.get("result")).get("duplicate").getAsBoolean())
                return "duplicate email";


            // [190412][HKPARK] 이메일 인증
            JsonObject emailObj = new JsonObject();
            emailObj.addProperty("sender", SENDER_EMAIL_ADDR);
            emailObj.addProperty("password", SENDER_EMAIL_PASS);
            emailObj.addProperty("receiver", email);
            emailObj.addProperty("title", "웹플리케이션 인증 메일입니다.");
            emailObj.addProperty("content", "안녕하세요, "+ userName + "님!<br>" +
                    "저희 웹플리케이션에 가입해주셔서 감사합니다.<br>" +
                    "이메일 인증을 하시려면 아래의 링크를 클릭해주세요.<br><br>" +
                    "<a href='http://parkkiho.asuscomm.com/verify/" + Base64Utils.encodeToString((userId + ":" + password).getBytes()) + "'>인증하기</a>");

            Thread logThread = new Thread(() ->
                    RestUtil.requestPost(addr + "/api/mail/send", headers, emailObj));
            logThread.start();

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }

    @RequestMapping(value = {"send_verify_mail"}, method = RequestMethod.POST)
    public @ResponseBody
    String sendVerifyMailGet(Model model, HttpSession session){
        if(session.getAttribute("users") == null)
            return "login first";

        try{
            JsonObject users = (JsonObject) session.getAttribute("users");
            String userId = users.get("userId").getAsString();
            String password = users.get("password").getAsString();
            String email = users.get("email").getAsString();
            String userName = users.get("userName").getAsString();

            JsonObject emailObj = new JsonObject();
            emailObj.addProperty("sender", SENDER_EMAIL_ADDR);
            emailObj.addProperty("password", SENDER_EMAIL_PASS);
            emailObj.addProperty("receiver", email);
            emailObj.addProperty("title", "웹플리케이션 인증 메일입니다.");
            emailObj.addProperty("content", "안녕하세요, "+ userName + "님!<br>" +
                    "이메일 인증메일입니다.<br>" +
                    "이메일 인증을 하시려면 아래의 링크를 클릭해주세요.<br><br>" +
                    "<a href='http://parkkiho.asuscomm.com/verify/" + Base64Utils.encodeToString((userId + ":" + password).getBytes()) + "'>인증하기</a>");

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            Thread logThread = new Thread(() ->
                    RestUtil.requestPost(addr + "/api/mail/send", headers, emailObj));
            logThread.start();

        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        return "ok";
    }
}