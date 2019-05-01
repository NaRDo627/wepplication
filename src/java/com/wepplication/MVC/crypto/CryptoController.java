package com.wepplication.MVC.crypto;

import com.wepplication.Util.LogUtil;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/crypto")
public class CryptoController {

    @RequestMapping(value = {"/decrypt"}, method = RequestMethod.GET)
    public String cryptoDecryptGet(Model model, HttpSession session){
        return "/crypto/decrypt";
    }

    @RequestMapping(value = {"/encrypt"}, method = RequestMethod.GET)
    public String cryptoEncryptGet(Model model, HttpSession session){
        return "/crypto/encrypt";
    }

    @RequestMapping(value = {"/encryptWord"}, method = RequestMethod.GET)
    public String cryptoEncryptWordGet(Model model, HttpSession session){

        // 히스토리, 로그 업데이트
        if(session.getAttribute("users") != null) {
            JSONObject userObj = (JSONObject)session.getAttribute("users");
            Thread logThread = new Thread(() ->
                    LogUtil.writeAllActivityLog(userObj, "/crypto/encryptWord", "문장 암호화"));
            logThread.start();
        }

        return "/crypto/encryptWord";
    }

    @RequestMapping(value = {"/encryptFile"}, method = RequestMethod.GET)
    public String cryptoEncryptFileGet(Model model, HttpSession session){

        // 히스토리, 로그 업데이트
        if(session.getAttribute("users") != null) {
            JSONObject userObj = (JSONObject)session.getAttribute("users");
            Thread logThread = new Thread(() ->
                    LogUtil.writeAllActivityLog(userObj, "/crypto/encryptFile", "파일 암호화"));
            logThread.start();
        }

        return "/crypto/encryptFile";
    }
}