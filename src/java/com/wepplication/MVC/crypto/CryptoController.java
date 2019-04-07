package com.wepplication.MVC.crypto;

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
    // TODO 현재 유저정보가 있다면 이 URL 저장
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
        return "/crypto/encryptWord";
    }

    @RequestMapping(value = {"/encryptFile"}, method = RequestMethod.GET)
    public String cryptoEncryptFileGet(Model model, HttpSession session){
        return "/crypto/encryptFile";
    }
}