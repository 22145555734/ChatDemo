package com.YinnnH.config;

import jakarta.servlet.http.HttpSession;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    private int teasr2;
    public int sss = 0;
    public int sss1 = 0;
    @Override
    public void modifyHandshake(ServerEndpointConfig serverEndpointConfig, HandshakeRequest handshakeRequest, HandshakeResponse handshakeResponse) {
        //获取httpSession对象
        HttpSession httpSession = (HttpSession) handshakeRequest.getHttpSession();
        //将httpsession保存到serendconfig;
        serverEndpointConfig.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
