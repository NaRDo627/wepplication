package com.wepplication.MVC.Main;

import com.wepplication.Util.EncryptUtil;
import com.wepplication.Util.RestUtil;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
            JSONObject obj = RestUtil.requestPut(addr + "/users/verification", headers);

            if((int)obj.get("res_code") != HttpURLConnection.HTTP_OK){
                return "redirect:/";
            }

            if(session.getAttribute("users") != null) {
                try{
                    JSONObject users = (JSONObject)session.getAttribute("users");
                    session.removeAttribute("users");
                    users.put("verfied", 1);
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
                      Model model, HttpSession session){

        try{
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"authorization", "Basic " + Base64Utils.encodeToString((userId + ":" + EncryptUtil.encryptByMd5("whfwkrtlfjbb" + password)).getBytes())});
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            JSONObject obj = RestUtil.requestGet(addr + "/users/myinfo", headers);
            if((Boolean)obj.get("error"))
                throw new Exception("서버 오류입니다. 나중에 다시 시도해 주세요.\n문제가 계속되면 관리자에게 문의 바랍니다.");

            if ((int)obj.get("res_code") == HttpURLConnection.HTTP_UNAUTHORIZED){
                return "unauthorized";
            }else if((int)obj.get("res_code") != HttpURLConnection.HTTP_OK){
                return (String)obj.get("res_msg");
            }

            session.setAttribute("users", obj.get("result"));
            Integer uno = (Integer)((JSONObject)obj.get("result")).get("uno");

            // [190410][HKPARK] user_membership 레코드가 없으면 업데이트
            obj = RestUtil.requestGet(addr + "/user_membership/" + (Integer)((JSONObject)obj.get("result")).get("uno"), headers);
            JSONObject userMemberShipObj = null;
            if((int)obj.get("res_code") != HttpURLConnection.HTTP_OK) {
                userMemberShipObj = new JSONObject();
                userMemberShipObj.put("uno", uno);
                userMemberShipObj.put("mno", 1);
                userMemberShipObj.put("isAutoSubscribe", 0);
                obj = RestUtil.requestPost(addr + "/user_membership/", headers, userMemberShipObj);
                userMemberShipObj = (JSONObject)obj.get("result");
            } else
                userMemberShipObj = (JSONObject) obj.get("result");

            JSONObject membershipInfo = (JSONObject) RestUtil.requestGet(addr + "/membership/" + userMemberShipObj.get("mno"), headers).get("result");
            userMemberShipObj.put("mno", membershipInfo);
            session.setAttribute("user_membership", userMemberShipObj);

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
            JSONObject jsonObject = new JSONObject();
            for( Map.Entry<String, String> entry : memberForm.entrySet() ) {
                String key = entry.getKey();
                Object value = entry.getValue();
                jsonObject.put(key, value);
            }

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            JSONObject obj = RestUtil.requestPost(addr + "/users", headers, jsonObject);
            if((int)obj.get("res_code") != HttpURLConnection.HTTP_OK){
                return (String)obj.get("res_msg");
            }

            String password = (String)((JSONObject)obj.get("result")).get("password");

            // 이메일 중복 체크
            obj = RestUtil.requestGet(addr + "/users/check_duplicate_email/" + email, headers);
            if((int)obj.get("res_code") != HttpURLConnection.HTTP_OK){
                return (String)obj.get("res_msg");
            }

            if((Boolean)((JSONObject)obj.get("result")).get("duplicate"))
                return "duplicate email";


            // [190412][HKPARK] 이메일 인증
            JSONObject emailObj = new JSONObject();
            emailObj.put("sender", SENDER_EMAIL_ADDR);
            emailObj.put("password", SENDER_EMAIL_PASS);
            emailObj.put("receiver", email);
            emailObj.put("title", "웹플리케이션 인증 메일입니다.");
            emailObj.put("content", "안녕하세요, "+ userName + "님!\n" +
                    "저희 웹플리케이션에 가입해주셔서 감사합니다.\n" +
                    "이메일 인증을 하시려면 아래의 링크를 클릭해주세요.\n\n" +
                    "http://localhost:8080/verify/" + Base64Utils.encodeToString((userId + ":" + password).getBytes()));

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
            JSONObject users = (JSONObject) session.getAttribute("users");
            String userId = (String)users.get("userId");
            String password = (String)users.get("password");
            String email = (String)users.get("email");
            String userName = (String)users.get("userName");

            JSONObject emailObj = new JSONObject();
            emailObj.put("sender", SENDER_EMAIL_ADDR);
            emailObj.put("password", SENDER_EMAIL_PASS);
            emailObj.put("receiver", email);
            emailObj.put("title", "웹플리케이션 인증 메일입니다.");
            emailObj.put("content", "안녕하세요, "+ userName + "님!\n" +
                    "이메일 인증메일입니다.\n" +
                    "이메일 인증을 하시려면 아래의 링크를 클릭해주세요.\n\n" +
                    "http://localhost:8080/verify/" + Base64Utils.encodeToString((userId + ":" + password).getBytes()));

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