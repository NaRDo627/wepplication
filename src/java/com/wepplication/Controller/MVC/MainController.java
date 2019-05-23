package com.wepplication.Controller.MVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import com.wepplication.Domain.MemberShip;
import com.wepplication.Domain.UserMemberShip;
import com.wepplication.Domain.Users;
import com.wepplication.Form.AuthInfo;
import com.wepplication.Util.DateTimeUtil;
import com.wepplication.Util.EncryptUtil;
import com.wepplication.Util.RestUtil;
//import org.codehaus.jettison.json.JSONObject;
//import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

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
    //private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    /* GoogleLogin */
    @Inject
    private AuthInfo authInfo;

    @Autowired
    private GoogleConnectionFactory googleConnectionFactory;

    @Autowired
    private OAuth2Parameters googleOAuth2Parameters;


    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Timestamp.class, (JsonDeserializer<Timestamp>) (json, typeOfT, context) -> new Timestamp(json.getAsJsonPrimitive().getAsLong()))
            .registerTypeAdapter(Timestamp.class, (JsonSerializer<Timestamp>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
            .create();

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String indexGet(Model model, HttpSession session){
        if(session.getAttribute("user_membership") != null){
            UserMemberShip userMemberShip = (UserMemberShip) session.getAttribute("user_membership");
            String addr = API_ADDRESS + ":" + API_PORT;
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            JsonObject obj = (JsonObject) RestUtil.requestGet(addr + "/membership/" + userMemberShip.getMno(), headers);
            MemberShip membershipInfo = gson.fromJson(obj.get("result"), MemberShip.class);
            model.addAttribute("membership", membershipInfo);
        }

        return "index";
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.GET)
    public String loginGet(Model model, HttpSession session){
        /* 구글code 발행 */
        OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
        String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);

        model.addAttribute("google_url", url);

        return "login";
    }

    @RequestMapping(value = {"oauth2login"}, method = RequestMethod.GET)
    public String oauth2LoginGet(HttpServletRequest request,
                                 Model model, HttpSession session){

        String code = request.getParameter("code");
        System.out.println(code);

        List<String[]> headers = new ArrayList<>();
        //headers.add(new String[]{"Accept", "*/*"});
       // headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
        headers.add(new String[]{"Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString()});

        String requestBodyString = "code=" + code + "&" +
                "client_id=" + authInfo.getClientId() + "&" +
                "client_secret=" + authInfo.getClientSecret() + "&" +
                "redirect_uri=" +  googleOAuth2Parameters.getRedirectUri() + "&" +
                "grant_type=" + "authorization_code";

        JsonObject obj = (JsonObject) RestUtil.requestPost("https://www.googleapis.com/oauth2/v4/token", headers, requestBodyString);
        if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK)
            return "redirect:/";

        JsonObject result = obj.getAsJsonObject("result");
       /* //RestTemplate을 사용하여 Access Token 및 profile을 요청한다.
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", code);
        parameters.add("client_id", authInfo.getClientId());
        parameters.add("client_secret", authInfo.getClientSecret());
        parameters.add("redirect_uri", googleOAuth2Parameters.getRedirectUri());
        parameters.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED.toString());
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange("https://www.googleapis.com/oauth2/v4/token", HttpMethod.POST, requestEntity, Map.class);
        Map<String, Object> responseMap = responseEntity.getBody();
*/
        // id_token 라는 키에 사용자가 정보가 존재한다.
        // 받아온 결과는 JWT (Json Web Token) 형식으로 받아온다. 콤마 단위로 끊어서 첫 번째는 현 토큰에 대한 메타 정보, 두 번째는 우리가 필요한 내용이 존재한다.
        // 세번째 부분에는 위변조를 방지하기 위한 특정 알고리즘으로 암호화되어 사이닝에 사용한다.
        //Base 64로 인코딩 되어 있으므로 디코딩한다.

        String[] tokens = result.get("id_token").getAsString().split("\\.");
        try{
            String body = new String(Base64Utils.decode(tokens[1].getBytes()), StandardCharsets.UTF_8);

            System.out.println(tokens.length);
            System.out.println(new String(Base64Utils.decode(tokens[0].getBytes()), StandardCharsets.UTF_8));
            System.out.println(new String(Base64Utils.decode(tokens[1].getBytes()), StandardCharsets.UTF_8));
        } catch (Exception e) {

        }




        //Jackson을 사용한 JSON을 자바 Map 형식으로 변환
      /*  ObjectMapper mapper = new ObjectMapper();
        Map<String, String> result = mapper.readValue(body, Map.class);
        System.out.println(result.get(""));
*/
        /*// 구글 로그인 처리
        System.out.println("it works! code:" + code);
        OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(), null);
        String accessToken = accessGrant.getAccessToken();
        Long expireTime = accessGrant.getExpireTime();
        if (expireTime != null && expireTime < System.currentTimeMillis())
        {
            accessToken = accessGrant.getRefreshToken();
            //logger.info("accessToken is expired. refresh token = {}" , accessToken);
        }

        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
        Plus plus = new Plus.builder(new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("Google-PlusSample/1.0")
                .build();*/

      /*  Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
        Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
        PlusOperations plusOperations = google.plusOperations();
        Person person = plusOperations.getGoogleProfile();*/


        return "index";
    }

    @RequestMapping(value = {"member"}, method = RequestMethod.GET)
    public String memberGet(Model model, HttpSession session){
        return "member";
    }

    @RequestMapping(value = {"findMember"}, method = RequestMethod.GET)
    public String findMemberGet(Model model, HttpSession session){

        return "findMember";
    }

    @RequestMapping(value = {"profile"}, method = RequestMethod.GET)
    public String profileGet(Model model, HttpSession session){
        if(session.getAttribute("users") == null) {
            return "redirect:/login";
        }

        if(session.getAttribute("user_membership") != null){
            UserMemberShip userMemberShip = (UserMemberShip) session.getAttribute("user_membership");
            String addr = API_ADDRESS + ":" + API_PORT;
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            JsonObject obj = (JsonObject) RestUtil.requestGet(addr + "/membership/" + userMemberShip.getMno(), headers);
            MemberShip membershipInfo = gson.fromJson(obj.get("result"), MemberShip.class);
            model.addAttribute("membership", membershipInfo);
        }

        Users users = (Users)session.getAttribute("users");
        if(users.getVerified() == 1)
            model.addAttribute("verfied", true);
        model.addAttribute("tab_select", "home-tab");
        model.addAttribute("verfied", false);

        return "profile";
    }

    @RequestMapping(value = {"subscribe"}, method = RequestMethod.GET)
    public String settingGet(Model model, HttpSession session){
        if(session.getAttribute("users") == null) {
            return "redirect:/login";
        }

        if(session.getAttribute("user_membership") != null){
            UserMemberShip userMemberShip = (UserMemberShip) session.getAttribute("user_membership");
            String addr = API_ADDRESS + ":" + API_PORT;
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            JsonObject obj = (JsonObject) RestUtil.requestGet(addr + "/membership/" + userMemberShip.getMno(), headers);
            MemberShip membershipInfo = gson.fromJson(obj.get("result"), MemberShip.class);
            model.addAttribute("membership", membershipInfo);
        }

        Users users = (Users)session.getAttribute("users");

        model.addAttribute("tab_select", "membership-tab");
        model.addAttribute("verfied", false);
        if(users.getVerified() == 1)
            model.addAttribute("verfied", true);

        return "profile";
    }

    @RequestMapping(value = {"logout"}, method = RequestMethod.GET)
    public String logoutGet(Model model, HttpSession session){
        session.removeAttribute("users");
        session.removeAttribute("user_membership");

        return "redirect:/login";
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
                Users users = (Users)session.getAttribute("users");
                users.setVerified(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "/";
        }

        return "verified";
    }

    @RequestMapping(value = {"findMemberID"}, method = RequestMethod.POST)
    public String findMemberIDPost(@RequestParam("userName") String userName,
                                   @RequestParam("email") String email, Model model){
        try{
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            String email_replaced = email.replace(".", "*");
            userName = URLEncoder.encode(userName, StandardCharsets.UTF_8.toString());
            JsonObject obj = (JsonObject) RestUtil.requestGet(apiAddress + "/users/userId?userName=" + userName + "&userEmail=" + email_replaced , headers);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                model.addAttribute("found", false);
                return "findMemberID";
            }
            String foundId = obj.get("result").getAsString();
            Integer maskLength = foundId.length() - 3;
            foundId = foundId.substring(0, maskLength) + "***";
            //foundId = foundId.replaceAll("(?<=^\\d{3,})\\d", "*");
            model.addAttribute("found", true);
            model.addAttribute("foundID", foundId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "findMemberID";
    }

    @RequestMapping(value = {"findMemberPW/{encUno}"}, method = RequestMethod.GET)
    public String findMemberPWGet(@PathVariable("encUno") String encUno, Model model){
        try{
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            encUno = new String(Base64Utils.decodeFromString(encUno));
            Integer uno = Integer.parseInt(encUno);
            JsonObject obj = (JsonObject) RestUtil.requestGet(apiAddress + "/users/" + uno, headers);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return "redirect:/";
            }
            model.addAttribute("uno", uno);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }

        return "findMemberPW";
    }

    @RequestMapping(value = {"findMemberPW"}, method = RequestMethod.POST)
    public @ResponseBody
    String findMemberPWPost(@RequestParam("uno") Integer uno,
                            @RequestParam("newPassword") String newPassword,
                            Model model){
        try{
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            JsonObject obj = (JsonObject) RestUtil.requestGet(apiAddress + "/users/" + uno, headers);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return "redirect:/";
            }
            Users users = gson.fromJson(obj.getAsJsonObject("result"), Users.class);
            users.setPassword(EncryptUtil.encryptByMd5("whfwkrtlfjbb" + newPassword));
            obj = (JsonObject)RestUtil.requestPut(apiAddress + "/users", headers, gson.toJson(users));
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return obj.get("res_msg").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }

    /* api */
    @RequestMapping(value = {"login"}, method = RequestMethod.POST, produces = "application/text;charset=utf-8")
    public @ResponseBody
    String loginPost(@RequestParam("userId") String userId,
                      @RequestParam("password") String password,
                     @RequestParam("autoLogin") Integer autoLogin,
                      Model model, HttpSession session, HttpServletResponse response) {

        try{
            JsonParser parser = new JsonParser();
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"authorization", "Basic " + Base64Utils.encodeToString((userId + ":" + EncryptUtil.encryptByMd5("whfwkrtlfjbb" + password)).getBytes())});
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String addr = API_ADDRESS + ":" + API_PORT;
            JsonObject obj = (JsonObject) RestUtil.requestGet(addr + "/users/myinfo", headers);
            if(obj.get("res_code").getAsInt() == HttpURLConnection.HTTP_UNAUTHORIZED){
                return "unauthorized";
            }else if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return obj.get("res_msg").getAsString();
            }
            else if(obj.get("error").getAsBoolean())
                throw new Exception("서버 오류입니다. 나중에 다시 시도해 주세요.\n문제가 계속되면 관리자에게 문의 바랍니다.");

            JsonObject result = obj.get("result").getAsJsonObject();

            Users users = gson.fromJson(result, Users.class);

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
                result = ((JsonObject)RestUtil.requestPost(addr + "/user_membership/", headers, gson.toJson(userMemberShip))).
                        getAsJsonObject("result");

                userMemberShip = gson.fromJson(result, UserMemberShip.class);
            } else{
                result = obj.getAsJsonObject("result");
                userMemberShip = gson.fromJson(result, UserMemberShip.class);
            }

            session.setAttribute("user_membership", userMemberShip);

            if(autoLogin == 1){
                Integer amount = 60 * 60 * 24 * 7; // 7 days
                Timestamp sessionTimeUntil = new Timestamp(DateTimeUtil.now().getTime() + amount);
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


            // 이메일 중복 체크
            String email_replaced = email.replace(".", "*");
            JsonObject obj = (JsonObject)RestUtil.requestGet(addr + "/users/check_duplicate_email/" + email_replaced, headers);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return obj.get("res_msg").getAsString();
            }

            if(obj.getAsJsonObject("result").get("duplicate").getAsBoolean())
                return "duplicate email";

            obj = (JsonObject)RestUtil.requestPost(addr + "/users", headers, jsonObject);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return obj.get("res_msg").getAsString();
            }

            Users users = gson.fromJson((obj.get("result")), Users.class);
            String password = users.getPassword();

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

    @RequestMapping(value = {"changePW"}, method = RequestMethod.POST)
    public @ResponseBody
    String changePWPost(@RequestParam("curPassword") String curPassword,
                      @RequestParam("newPassword") String newPassword, HttpSession session){
        try{
            if(session.getAttribute("users") == null) {
                return "login_first";
            }
            Users users = (Users)session.getAttribute("users");
            if(!users.getPassword().equals(EncryptUtil.encryptByMd5("whfwkrtlfjbb" + curPassword)))
                return "wrong_user";

            users.setPassword(EncryptUtil.encryptByMd5("whfwkrtlfjbb" + newPassword));

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            RestUtil.requestPut(apiAddress + "/users", headers, gson.toJson(users));
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }

    @RequestMapping(value = {"sendChangePWMail"}, method = RequestMethod.POST)
    public @ResponseBody
    String sendChangePWMailPost(@RequestParam("userID") String userID,
                        @RequestParam("email") String email, HttpSession session){
        try{
            String email_replaced = email.replace(".", "*");
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            JsonObject obj = (JsonObject) RestUtil.requestGet(apiAddress + "/users/uno?userID=" + userID + "&userEmail=" + email_replaced, headers);
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK){
                return "no_user_exists";
            }
            Integer uno = obj.get("result").getAsInt();

            // sending mail..
            JsonObject emailObj = new JsonObject();
            emailObj.addProperty("sender", SENDER_EMAIL_ADDR);
            emailObj.addProperty("password", SENDER_EMAIL_PASS);
            emailObj.addProperty("receiver", email);
            emailObj.addProperty("title", "웹플리케이션 비밀번호 분실 관련 메일입니다.");
            emailObj.addProperty("content", "비밀번호 초기화 메일입니다.<br>" +
                    "비밀번호를 초기화를 하시려면 아래의 링크를 클릭해주세요.<br><br>" +
                    "<a href='http://parkkiho.asuscomm.com/findMemberPW/" + Base64Utils.encodeToString((uno.toString()).getBytes()) + "'>비밀번호 초기화</a>");

            String addr = API_ADDRESS + ":" + API_PORT;
            Thread logThread = new Thread(() ->
                    RestUtil.requestPost(addr + "/api/mail/send", headers, emailObj));
            logThread.start();


        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }

    @RequestMapping(value = {"modifyAutoRenewal"}, method = RequestMethod.POST)
    public @ResponseBody
    String modifyAutoRenewalPost(@RequestParam("autoRenewal") Integer autoRenewal, HttpSession session){
        try{
            if(session.getAttribute("users") == null ||
                    session.getAttribute("user_membership") == null) {
                return "login_first";
            }
            UserMemberShip userMemberShip = (UserMemberShip)session.getAttribute("user_membership");
            userMemberShip.setIsAutoSubscribe(autoRenewal);

            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            String apiAddress = API_ADDRESS  + ":" + API_PORT;
            JsonObject obj = (JsonObject) RestUtil.requestPut(apiAddress + "/user_membership", headers, gson.toJson(userMemberShip));
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK)
                throw new Exception("Request_ERROR");
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }

    @RequestMapping(value = {"registerMemberShip"}, method = RequestMethod.POST)
    public @ResponseBody
    String registerMemberShipPost(@RequestParam("mno") Integer mno, HttpSession session){
        try{
            if(session.getAttribute("users") == null ||
                    session.getAttribute("user_membership") == null) {
                return "login_first";
            }
            String addr = API_ADDRESS + ":" + API_PORT;
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"Accept", "*/*"});
            headers.add(new String[]{"X-Requested-With", "XMLHttpRequest"});
            UserMemberShip userMemberShip = (UserMemberShip)session.getAttribute("user_membership");
            JsonObject obj = (JsonObject) RestUtil.requestGet(addr + "/membership/" + userMemberShip.getMno(), headers);
            MemberShip membershipInfo = gson.fromJson(obj.get("result"), MemberShip.class);

            userMemberShip.setMno(mno);
            userMemberShip.setStartTime(DateTimeUtil.now());
            userMemberShip.setEndTime(new Timestamp(DateTimeUtil.now().getTime() + membershipInfo.getDuration() * 60000));

            obj = (JsonObject) RestUtil.requestPut(addr + "/user_membership", headers, gson.toJson(userMemberShip));
            if(obj.get("res_code").getAsInt() != HttpURLConnection.HTTP_OK)
                throw new Exception("Request_ERROR");
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
            Users users = (Users) session.getAttribute("users");
            String userId = users.getUserId();
            String password = users.getPassword();
            String email = users.getEmail();
            String userName = users.getUserName();

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