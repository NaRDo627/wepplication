package com.wepplication.RESTful.Controller;


import com.wepplication.RESTful.Form.API.SSH.SSHRequestForm;
import com.wepplication.RESTful.Form.API.SSH.SSHResponseForm;
import com.wepplication.Util.EncryptUtil;
import com.wepplication.Util.SSHClientUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

@RestController
@RequestMapping("/api")
public final class APIController implements HttpSessionListener {

    /* SSH */
    private Map<String, SSHClientUtil> sshClientMap = new HashMap<String, SSHClientUtil>();

    @RequestMapping(value = {"/ssh/login"}, method = RequestMethod.POST)
    public ResponseEntity<SSHResponseForm> apiSSHLogin(@RequestBody SSHRequestForm sshRequestForm, HttpSession session) {
        SSHResponseForm sshResponseForm = new SSHResponseForm();

        if(session.getAttribute("token") != null) {
            sshResponseForm.setStatus("Already_used");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.IM_USED);
        }

        String token = EncryptUtil.encryptByMd5(new Date().toString());
        session.setAttribute("token", token);

        String host = sshRequestForm.getHostname();
        Integer port = (sshRequestForm.getPort() != null)? sshRequestForm.getPort(): 22;
        String username = sshRequestForm.getUsername();
        String password = sshRequestForm.getPassword();
        String charset = "utf-8";

        if(host == null || username == null || password == null)
            if(host == null){
                sshResponseForm.setStatus("Bad_Request");
                return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.BAD_REQUEST);
            }

        SSHClientUtil sshClientUtil = new SSHClientUtil(host,username,password,port);
        sshClientUtil.enableDebug();
        if(!sshClientUtil.ConnectSession()) {
            sshResponseForm.setStatus("Internal_Server_Error");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        sshClientMap.put(token, sshClientUtil);

        sshResponseForm.setStatus("OK");
        return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.OK);
    }

    @RequestMapping(value = {"/ssh/execute"}, method = RequestMethod.POST)
    public ResponseEntity<SSHResponseForm> apiSSHExec(@RequestBody SSHRequestForm sshRequestForm, HttpSession session) {
        SSHResponseForm sshResponseForm = new SSHResponseForm();

        if(session.getAttribute("token") == null) {
            sshResponseForm.setStatus("Login_First");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.UNAUTHORIZED);
        }

        String token = (String)session.getAttribute("token");
        SSHClientUtil sshClientUtil = sshClientMap.get(token);
        if(sshClientUtil == null) {
            sshResponseForm.setStatus("Internal_Server_Error");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(sshRequestForm.getCommand() == null) {
            sshResponseForm.setStatus("Bad_Request");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.BAD_REQUEST);
        }
        String command = sshRequestForm.getCommand();
        String response = sshClientUtil.exec(command);

        sshResponseForm.setStatus("OK");
        sshResponseForm.setResponse(response);
        return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.OK);
    }

    @RequestMapping(value = {"/ssh/logout"}, method = RequestMethod.GET)
    public ResponseEntity<SSHResponseForm> apiSSHlogout(HttpSession session) {
        SSHResponseForm sshResponseForm = new SSHResponseForm();

        if(session.getAttribute("token") == null) {
            sshResponseForm.setStatus("Login_First");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.UNAUTHORIZED);
        }

        String token = (String)session.getAttribute("token");
        SSHClientUtil sshClientUtil = sshClientMap.get(token);
        if(sshClientUtil == null) {
            sshResponseForm.setStatus("Internal_Server_Error");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        sshClientUtil.DisconnectSession();
        sshClientMap.remove(token);
        session.removeAttribute("token");

        sshResponseForm.setStatus("OK");
        return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.OK);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session Destroyed");
        sshClientMap.remove((String)(se.getSession().getAttribute("token")));
    }
}


