package com.wepplication.RESTful.Controller;

import com.jcraft.jsch.*;
import com.wepplication.RESTful.Domain.CloudFile;
import com.wepplication.RESTful.Form.API.SSH.SSHLoginRequestForm;
import com.wepplication.RESTful.Form.API.SSH.SSHLoginResponseForm;
import com.wepplication.RESTful.Utils.SSHClientUtil;
import me.saro.commons.ssh.SSHShell;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
public class APIController {

    /* SSH */
    @RequestMapping(value = {"/ssh/login"}, method = RequestMethod.POST)
    public ResponseEntity<SSHLoginResponseForm> apiSSHLogin(@RequestBody SSHLoginRequestForm sshLoginRequestForm) {
        SSHLoginResponseForm sshLoginResponseForm = new SSHLoginResponseForm();


        String host = sshLoginRequestForm.getHostname();
        Integer port = (sshLoginRequestForm.getPort() != null)? sshLoginRequestForm.getPort(): 22;
        String username = sshLoginRequestForm.getUsername();
        String password = sshLoginRequestForm.getPassword();
        String charset = "utf-8";

        if(host == null || username == null ||  password == null)
            if(host == null)
                return new ResponseEntity<SSHLoginResponseForm>(sshLoginResponseForm, HttpStatus.BAD_REQUEST);

        SSHClientUtil sshClientUtil = new SSHClientUtil(host,username,password,port);
        sshClientUtil.enableDebug();
        String ret = sshClientUtil.exec("df -h");

        sshLoginResponseForm.setStatus(ret);
        return new ResponseEntity<SSHLoginResponseForm>(sshLoginResponseForm, HttpStatus.OK);
    }

}


