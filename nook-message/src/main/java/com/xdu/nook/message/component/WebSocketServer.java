package com.xdu.nook.message.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/message/socket/{userid}")
@Component
public class WebSocketServer {

    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("userid") String userid) throws IOException {
        System.out.println("connection from " + userid + ": build");
        sessionMap.put(userid, session);
    }


    @OnClose
    public void onClose(Session session, @PathParam("userid") String userid) {
        System.out.println("connection from " + userid + ": close");
        sessionMap.remove(userid);
    }


    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userid") String username) {

    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public static void sendMessage(Session session,String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
}
