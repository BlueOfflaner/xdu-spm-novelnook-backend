package com.xdu.nook.message.component;

import com.xdu.nook.message.feign.UserClient;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketHelper {

    @Resource
    UserClient userClient;

    public HashMap<Long, Session> findAdmin() {
        HashMap<Long, Session> allAdmin = new HashMap<>();
        Map<String, Session> sessionMap = WebSocketServer.sessionMap;
        sessionMap.keySet().forEach(userid -> {
            Long userId = Long.parseLong(userid);
            boolean isAdmin = userClient.checkIsAdmin(userId);
            if (isAdmin) {
                allAdmin.put(userId, sessionMap.get(userid));
            }
        });
        return allAdmin;
    }

    public HashMap<Long, Session> findUser(Long userId) {
        Map<String, Session> sessionMap = WebSocketServer.sessionMap;
        HashMap<Long, Session> resultMap=new HashMap<>();
        sessionMap.keySet().forEach(key->{
            if(key.equals(""+userId)){
               resultMap.put(userId, sessionMap.get(key));
            }
        });
        return resultMap;
    }

    public void sendMessage(Session session, String msg) throws IOException {
        WebSocketServer.sendMessage(session, msg);
    }

}
