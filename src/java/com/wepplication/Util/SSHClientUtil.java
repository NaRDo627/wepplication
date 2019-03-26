package com.wepplication.Util;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.hibernate.tool.hbm2x.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SSHClientUtil {
    private String hostname = "";
    private String username = "";
    private String identity = null;
    private String password = null;
    private Integer port;
    private Boolean isDebugMode = false;
    private String lastError = "";
    private Session curSession = null;
    private String homeDirectory = null;
    private String lastDirectory = null;
    private String[] bannedCommandArray = {"vi", "vim", "man"};
    private List<String> bannedCommandList = new ArrayList<>(Arrays.asList(bannedCommandArray));

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public String getLastDirectory() {
        return lastDirectory;
    }

    public void setLastDirectory(String lastDirectory) {
        this.lastDirectory = lastDirectory;
    }

    public Session getCurSession() {
        return curSession;
    }

    public void setCurSession(Session curSession) {
        this.curSession = curSession;
    }

    public void enableDebug() {
        isDebugMode = true;
    }

    public void disableDebug() {
        isDebugMode = false;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
        this.password = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.identity = null;
    }

    public String getLastError() {
        return lastError;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public SSHClientUtil() {
        this.port = 22;
    }

    public SSHClientUtil(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = 22;
    }

    public SSHClientUtil(String hostname, String username, String password, Integer port) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /* public */
    public Boolean ConnectSession() {
        try {
            curSession = getSession();
            if(curSession.isConnected())
                return true;

            curSession.connect();
            homeDirectory = exec("pwd");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean DisconnectSession() {
        try {
            if(curSession == null)
                return true;
            if(!curSession.isConnected())
                return true;

            curSession.disconnect();
            curSession = null;
            homeDirectory = null;
            lastDirectory = null;
        } catch (Exception e) {
            e.printStackTrace();
            lastError = e.toString();
            return false;
        }
        return true;
    }

    public String exec(String command) {
        List<String> ret = exec(new String[] {command});
        if(ret.size() == 1)
            return ret.get(0);

        return null;
    }

    public List<String> exec(List<String> commands) {
        return exec(commands.toArray(new String[]{}));
    }

    public List<String> exec(String[] commands) {
        if(curSession == null)
            return null;

        List<String> ret = new ArrayList<String>();
        try {
            /*Session session = getSession();
            session.connect();*/
            // 현재 경로를 알기 위해 pwd 명령어 추가
            ArrayList<String> commandsList = new ArrayList<String>(Arrays.asList(commands));

            //for(String command:commands) {
            for(int i = 0; i < commandsList.size(); i++){
                Boolean isChangeDir = false;
                String command = commandsList.get(i);
                command = command.trim();

                // 허용하지 않는 명령어는 제외
                if(bannedCommandList.contains(command.split(" ")[0])) {
                    String alertMessage;
                    alertMessage = "명령 " + command.split(" ")[0] + "은(는) 아직 지원하지 않습니다!";
                    ret.add(alertMessage);
                    continue;
                }

                ChannelExec channelExec = (ChannelExec) curSession.openChannel("exec");
                channelExec.setPty(true);

                if(isDebugMode) System.out.println("command : " + command);

                if(command.split(" ")[0].equals("cd")) {
                    isChangeDir = true;
                    command += "&& pwd";
                }

                if(lastDirectory != null)
                    command = "cd " + lastDirectory + " && " + command;

                channelExec.setCommand(command);
                InputStream inputStream = channelExec.getInputStream();
                InputStream err = channelExec.getErrStream();
                channelExec.connect(5000);

                if(isDebugMode) System.out.print("stdout : ");
                String output="";
                byte[] buf = new byte[1024];
                Integer length = 0;
                while((length=inputStream.read(buf)) != -1) {
                    output += new String(buf, 0, length);
                    if(isDebugMode)
                        System.out.print(new String(buf,0,length));
                }
                if(isDebugMode)
                    if(IOUtils.toString(err).length() != 0)
                        System.out.println("\nerr : " + IOUtils.toString(err));
                String retStr = StringUtils.chop(output);

                if(!isChangeDir) {
                    ret.add(retStr);
                    channelExec.disconnect();
                    continue;
                }

                // 만약 output에 공백이 존재하면 에러로 간주 (리눅스 경로명에는 에러가 있을수 없기 때문)
                if(output.contains(" ")) {
                    ret.add(retStr);
                } else {
                    lastDirectory = retStr.replace(homeDirectory, "~");
                    ret.add("");
                }

                channelExec.disconnect();

            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    /* private */
    private Session getSession() throws JSchException{
        JSch jSch = new JSch();
        if (identity!=null) {
            jSch.addIdentity(identity);
        }

        Session session= jSch.getSession(username, hostname, port);
        session.setConfig("StrictHostKeyChecking", "no");
        if (password != null) session.setPassword(password);
        return session;
    }
}
