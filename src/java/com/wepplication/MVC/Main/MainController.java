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

    @RequestMapping(value = {"setting"}, method = RequestMethod.GET)
    public String settingGet(Model model, HttpSession session){
        model.addAttribute("tab_select", "settings-tab");
        return "profile";
    }

    @RequestMapping(value = {"logout"}, method = RequestMethod.GET)
    public String logoutGet(Model model, HttpSession session){
        session.removeAttribute("users");
/*        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("nickName");*/
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
            JSONObject obj = RestUtil.requestGet(API_ADDRESS + ":" + API_PORT + "/users/myinfo", headers);
            if((Boolean)obj.get("error"))
                throw new Exception("HTTP Request failed");


            if((int)obj.get("res_code") == HttpURLConnection.HTTP_OK){
                session.setAttribute("users", obj.get("result"));
            }else if ((int)obj.get("res_code") == HttpURLConnection.HTTP_UNAUTHORIZED){
                return "unauthorized";
            }else{
                return (String)obj.get("res_msg");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "ok";
    }
}