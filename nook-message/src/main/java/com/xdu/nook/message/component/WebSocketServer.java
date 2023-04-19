package com.xdu.nook.message.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/message/socket/{username}")
@Component
public class WebSocketServer {

    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessionMap.put(username, session);

        JSONObject result = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        result.put("users", jsonArray);
        sessionMap.keySet().stream().forEach(key -> {
            jsonArray.add(new JSONObject().put("username", key));

        });
        /*
            user=[{"username":"yasuo"},{"username":"yone"}]
         */
        sendAllMessage(result.toJSONString());
    }


    @OnClose
    public void onClose(Session sesinon, @PathParam("username") String username) {
        sessionMap.remove(username);
    }


    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username")String username){

    }


    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }


    private void sendAllMessage(String message_json) {
        try {
            for (Session session : sessionMap.values()) {
                session.getBasicRemote().sendText(message_json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
