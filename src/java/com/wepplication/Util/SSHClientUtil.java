package com.wepplication.Util;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.hibernate.tool.hbm2x.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
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
            curSession.connect();
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
            curSession.disconnect();
            curSession = null;
        } catch (Exception e) {
            e.printStackTrace();
            lastError = e.toString();
            return false;
        }
        return true;
    }

    public String exec(String command) {
        return exec(new String[] {command}).get(0);
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

            for(String command:commands) {
                ChannelExec channelExec = (ChannelExec) curSession.openChannel("exec");
                channelExec.setPty(true);
                if(isDebugMode) System.out.println("command : " + command);
                channelExec.setCommand(command);
                InputStream inputStream = channelExec.getInputStream();
                InputStream err = channelExec.getErrStream();
                channelExec.connect(3000);

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

                ret.add(StringUtils.chop(output));
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
