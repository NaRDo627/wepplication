package com.wepplication.MVC.crypto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/crypto")
public class CryptoController {

    @RequestMapping(value = {"/decrypt"}, method = RequestMethod.GET)
    public String cryptoDecryptGet(){
        return "/crypto/decrypt";
    }

    @RequestMapping(value = {"/encrypt"}, method = RequestMethod.GET)
    public String cryptoEncryptGet(){
        return "/crypto/encrypt";
    }
}