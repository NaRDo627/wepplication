package com.wepplication.MVC.Main;

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
            URL object=new URL(API_ADDRESS + ":" + API_PORT + "/users/myinfo");

            HttpURLConnection con = (HttpURLConnection) object.openConnection();

            con.setDoOutput(true);
            con.setDoInput(true);
            //con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("authorization", "Basic " + Base64Utils.encodeToString((userId + ":" + password).getBytes()));
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestMethod("GET");


            StringBuilder sb = new StringBuilder();

            int HttpResult =con.getResponseCode();

            if(HttpResult ==HttpURLConnection.HTTP_OK){

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                br.close();

                JSONObject users = new JSONObject(sb.toString());
                System.out.println(""+sb.toString());

                session.setAttribute("users", users);
            }else if (HttpResult ==HttpURLConnection.HTTP_UNAUTHORIZED){
                System.out.println(con.getResponseMessage());
                return "unauthorized";
            }else{
                System.out.println(con.getResponseMessage());
                return con.getResponseMessage();
            }
        } catch (Exception e) {
            return e.toString();
        }

        return "ok";
    }

}