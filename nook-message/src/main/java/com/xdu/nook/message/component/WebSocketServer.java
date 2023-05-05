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

    }


    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {

    }


    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username")String username){

    }


    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }


    private void sendAllMessage(String message_json) {

    }
}
