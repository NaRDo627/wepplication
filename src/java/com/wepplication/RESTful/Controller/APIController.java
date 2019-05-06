package com.wepplication.RESTful.Controller;


import com.wepplication.RESTful.Exception.BadRequestException;
import com.wepplication.RESTful.Exception.ForbiddenException;
import com.wepplication.RESTful.Exception.ImUsedException;
import com.wepplication.RESTful.Exception.UnauthorizedException;
import com.wepplication.RESTful.Form.API.Mail.MailRequestForm;
import com.wepplication.RESTful.Form.API.SSH.SSHRequestForm;
import com.wepplication.RESTful.Form.API.SSH.SSHResponseForm;
import com.wepplication.Util.EncryptUtil;
import com.wepplication.Util.SSHClientUtil;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
                return new ResponseEntity<>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if(sshRequestForm.getCommand() == null) {
                sshResponseForm.setStatus("Bad_Request");
                sshResponseForm.setError(true);
                return new ResponseEntity<>(sshResponseForm, HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>(sshResponseForm, HttpStatus.OK);

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
                    return new ResponseEntity<>(sshResponseForm, HttpStatus.UNAUTHORIZED);
                }
                sshResponseForm.setStatus("Login_First");
                sshResponseForm.setError(true);
                return new ResponseEntity<>(sshResponseForm, HttpStatus.UNAUTHORIZED);
            }
            else
                token = (String)session.getAttribute("token");

            SSHClientUtil sshClientUtil = sshClientMap.get(token);
            if(sshClientUtil == null) {
                sshResponseForm.setStatus("Internal_Server_Error");
                sshResponseForm.setError(true);
                return new ResponseEntity<>(sshResponseForm, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            sshClientUtil.DisconnectSession();
            sshClientMap.remove(token);
            session.removeAttribute("token");

            sshResponseForm.setStatus("OK");
            return new ResponseEntity<>(sshResponseForm, HttpStatus.OK);

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

    @CrossOrigin
    @RequestMapping(value = {"/mail/send"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> apiMailPost(@RequestBody MailRequestForm requestForm) {
        if(requestForm.getSender() == null || requestForm.getSender().length() == 0 ||
                requestForm.getPassword() == null || requestForm.getPassword().length() == 0 ||
                requestForm.getTitle() == null || requestForm.getTitle().length() == 0 ||
                requestForm.getContent() == null || requestForm.getContent().length() == 0 ||
                requestForm.getReceiver() == null || requestForm.getReceiver().length() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        try{
            JSONObject response = new JSONObject();
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", 465);
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            // SMTP 세션 생성
            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(requestForm.getSender(), requestForm.getPassword());
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(requestForm.getSender()));


            message.addRecipient(Message.RecipientType.TO, new InternetAddress(requestForm.getReceiver()));
            message.setSubject(requestForm.getTitle(), "UTF-8");
            message.setContent(requestForm.getContent() + "<br><br>이 메일은 자동발송 되었습니다.", "text/html; charset=utf-8");
            Transport.send(message);
            response.put("result", "ok");
            System.out.println("mail sent successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject response = new JSONObject();
            response.put("error", e.getCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /* Exception */
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


