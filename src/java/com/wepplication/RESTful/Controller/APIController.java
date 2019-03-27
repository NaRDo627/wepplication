package com.wepplication.RESTful.Controller;


import com.wepplication.RESTful.Exception.BadRequestException;
import com.wepplication.RESTful.Exception.ForbiddenException;
import com.wepplication.RESTful.Exception.ImUsedException;
import com.wepplication.RESTful.Exception.UnauthorizedException;
import com.wepplication.RESTful.Form.API.SSH.SSHRequestForm;
import com.wepplication.RESTful.Form.API.SSH.SSHResponseForm;
import com.wepplication.Util.EncryptUtil;
import com.wepplication.Util.SSHClientUtil;

import org.json.simple.JSONObject;
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

    @CrossOrigin
    @RequestMapping(value = {"/ssh/login"}, method = RequestMethod.POST)
    public ResponseEntity<SSHResponseForm> apiSSHLogin(@RequestBody SSHRequestForm sshRequestForm, HttpSession session) {
        SSHResponseForm sshResponseForm = new SSHResponseForm();

        try{
            if(session.getAttribute("token") != null) {
                throw new ImUsedException("이미 사용중인 세션입니다.");
            }

            String token = EncryptUtil.encryptByMd5(new Date().toString());
            session.setAttribute("token", token);

            String host = sshRequestForm.getHostname();
            Integer port = (sshRequestForm.getPort() != null)? sshRequestForm.getPort(): 22;
            String username = sshRequestForm.getUsername();
            String password = sshRequestForm.getPassword();
            String charset = "utf-8";

            if(host == null || username == null || password == null ||
                    host.length() == 0 || username.length() == 0 || password.length() == 0) {
                throw new BadRequestException("요청 파라미터가 맞지 않습니다.");
                }

            SSHClientUtil sshClientUtil = new SSHClientUtil(host,username,password,port);
            sshClientUtil.enableDebug();
            if(!sshClientUtil.ConnectSession()) {
                throw new ForbiddenException(sshClientUtil.getLastError());
            }

            sshClientMap.put(token, sshClientUtil);

            sshResponseForm.setStatus("OK");
            JSONObject result = new JSONObject();
            result.put("token", token);
            JSONObject environment = new JSONObject();
            environment.put("user", sshClientUtil.getUsername());
            environment.put("hostname", sshClientUtil.getHostname());
            environment.put("path", sshClientUtil.getLastDirectory());
            result.put("environment", environment);
            sshResponseForm.setResult(result);
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.OK);

        } catch(ImUsedException e) {
            return handleException(e);
        }catch(BadRequestException e) {
            return handleException(e);
        }
        catch(ForbiddenException e) {
            return handleException(e);
        }
        catch(Exception e) {
            return handleException(e);
        }
    }

    @CrossOrigin
    @RequestMapping(value = {"/ssh/execute"}, method = RequestMethod.POST)
    public ResponseEntity<SSHResponseForm> apiSSHExec(@RequestBody SSHRequestForm sshRequestForm, HttpSession session) {
        SSHResponseForm sshResponseForm = new SSHResponseForm();
        try{
            String token = null;

            if(session.getAttribute("token") == null) {
                token = sshRequestForm.getToken();
                if(token == null) {
                    sshResponseForm.setStatus("Login_First");
                    return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.UNAUTHORIZED);
                }
            }else
                token = (String)session.getAttribute("token");
            SSHClientUtil sshClientUtil = sshClientMap.get(token);
            if(sshClientUtil == null) {
                sshResponseForm.setStatus("Internal_Server_Error");
                sshResponseForm.setError(true);
                return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if(sshRequestForm.getCommand() == null) {
                sshResponseForm.setStatus("Bad_Request");
                sshResponseForm.setError(true);
                return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.BAD_REQUEST);
            }
            String command = sshRequestForm.getCommand();
            String[] commands = command.split("&&");
            List<String> responseList = sshClientUtil.exec(commands);
            if (responseList == null){
                sshResponseForm.setResponse("");
                System.out.println("empty response");
            } else {
                String response = "";
                for(int i = 0; i < responseList.size(); i++) {
                    if(i!=0)
                        response += "\n";
                    response += responseList.get(i);
                }

                sshResponseForm.setResponse(response);
            }

            sshResponseForm.setStatus("OK");
            JSONObject result = new JSONObject();
            result.put("token", token);
            JSONObject environment = new JSONObject();
            environment.put("user", sshClientUtil.getUsername());
            environment.put("hostname", sshClientUtil.getHostname());
            environment.put("path", sshClientUtil.getLastDirectory());
            result.put("environment", environment);
            sshResponseForm.setResult(result);
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.OK);

        } catch(Exception e) {
            return handleException(e);
        }
    }

    @CrossOrigin
    @RequestMapping(value = {"/ssh/logout"}, method = RequestMethod.POST)
    public ResponseEntity<SSHResponseForm> apiSSHlogout(@RequestBody SSHRequestForm sshRequestForm, HttpSession session) {
        SSHResponseForm sshResponseForm = new SSHResponseForm();

        try{
            String token = null;
            if(session.getAttribute("token") == null) {
                token = sshRequestForm.getToken();
                if(token == null) {
                    sshResponseForm.setStatus("Login_First");
                    return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.UNAUTHORIZED);
                }
                sshResponseForm.setStatus("Login_First");
                sshResponseForm.setError(true);
                return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.UNAUTHORIZED);
            }
            else
                token = (String)session.getAttribute("token");

            SSHClientUtil sshClientUtil = sshClientMap.get(token);
            if(sshClientUtil == null) {
                sshResponseForm.setStatus("Internal_Server_Error");
                sshResponseForm.setError(true);
                return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            sshClientUtil.DisconnectSession();
            sshClientMap.remove(token);
            session.removeAttribute("token");

            sshResponseForm.setStatus("OK");
            return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.OK);

        } catch(Exception e) {
            return handleException(e);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if(se.getSession().getAttribute("token") == null)
            return;

        String token = (String)se.getSession().getAttribute("token");
        SSHClientUtil sshClientUtil = sshClientMap.get(token);
        if(sshClientUtil != null)
            sshClientUtil.DisconnectSession();
        sshClientMap.remove(token);
    }

    private ResponseEntity<SSHResponseForm> handleException(ImUsedException e) {
        e.printStackTrace();
        SSHResponseForm sshResponseForm = new SSHResponseForm();
        sshResponseForm.setError(true);
        sshResponseForm.setStatus("Forbidden");
        JSONObject result = new JSONObject();
        result.put("cause", e.getMessage());
        result.put("stacktrace", e.getStackTrace());
        sshResponseForm.setResult(result);
        return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.IM_USED);
    }

    private ResponseEntity<SSHResponseForm> handleException(ForbiddenException e) {
        e.printStackTrace();
        SSHResponseForm sshResponseForm = new SSHResponseForm();
        sshResponseForm.setError(true);
        sshResponseForm.setStatus("Forbidden");
        JSONObject result = new JSONObject();
        result.put("cause", e.getMessage());
        result.put("stacktrace", e.getStackTrace());
        sshResponseForm.setResult(result);
        return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<SSHResponseForm> handleException(BadRequestException e) {
        e.printStackTrace();
        SSHResponseForm sshResponseForm = new SSHResponseForm();
        sshResponseForm.setError(true);
        sshResponseForm.setStatus("Forbidden");
        JSONObject result = new JSONObject();
        result.put("cause", e.getMessage());
        result.put("stacktrace", e.getStackTrace());
        sshResponseForm.setResult(result);
        return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<SSHResponseForm> handleException(Exception e) {
        e.printStackTrace();
        SSHResponseForm sshResponseForm = new SSHResponseForm();
        sshResponseForm.setError(true);
        sshResponseForm.setStatus("Internal_Server_Error");
        JSONObject result = new JSONObject();
        result.put("cause", e.getCause());
        result.put("stacktrace", e.getStackTrace());
        sshResponseForm.setResult(result);
        return new ResponseEntity<SSHResponseForm>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

