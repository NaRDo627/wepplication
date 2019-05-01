package com.wepplication.MVC.Main;

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


    /* api */
    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    public @ResponseBody
    String memberPost(@RequestParam("userId") String userId,
                      @RequestParam("password") String password,
                      Model model, HttpSession session){

        try{
            List<String[]> headers = new ArrayList<>();
            headers.add(new String[]{"authorization", "Basic " + Base64Utils.encodeToString((userId + ":" + password).getBytes())});
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

            // [190410][HKPARK] user_membership 레코드가 없으면 업데이트
            obj = RestUtil.requestGet(addr + "/user_membership/" + (Integer)((JSONObject)obj.get("result")).get("uno"), headers);
            JSONObject userMemberShipObj = (JSONObject) obj.get("result");
            if((int)obj.get("res_code") != HttpURLConnection.HTTP_OK) {
                userMemberShipObj = new JSONObject();
                userMemberShipObj.put("uno", (Integer)((JSONObject)obj.get("result")).get("uno"));
                userMemberShipObj.put("mno", 0);
                userMemberShipObj.put("isAutoSubscribe", 0);
                obj = RestUtil.requestPost(addr + "/user_membership/", headers, userMemberShipObj);
                userMemberShipObj = (JSONObject)obj.get("result");
            }
            JSONObject membershipInfo = (JSONObject) RestUtil.requestGet(addr + "/membership/" + userMemberShipObj.get("mno"), headers).get("result");
            userMemberShipObj.put("mno", membershipInfo);
            session.setAttribute("user_membership", userMemberShipObj);

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }
}