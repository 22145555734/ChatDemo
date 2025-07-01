package com.YinnnH.websocket;

import com.YinnnH.config.GetHttpSessionConfig;
import com.YinnnH.pojo.Message;
import com.YinnnH.utils.MessageUtils;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/user", configurator = GetHttpSessionConfig.class)
@Component
public class ChatEndpoint {

    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();
    private HttpSession httpSession;
    /**
     * 建立websocket后,被调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        //1.将session保存
        this.httpSession = (HttpSession)endpointConfig.getUserProperties().get(HttpSession.class.getName());
        String user = (String) this.httpSession.getAttribute("user");
        onlineUsers.put(user, session);
        //2.广播消息
        String message = MessageUtils.getMessage(true, null, getFriends());
        broadcastAllUsers(message);
    }


    public Set getFriends() {
        Set<String> set = onlineUsers.keySet();
        return set;
    }

    private void broadcastAllUsers(String message) {
        try {
            //遍历map集合
            Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();
            for (Map.Entry<String, Session> entry : entries) {
                //获取到所有用户对应的session
                Session session = entry.getValue();
                //发送消息
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            //记录日志
        }
    }


    /**
     * 浏览器发送消息到服务端,该方法被调用
     *
     * zhangsan -> lisi
     * @param message
     */
    @OnMessage
    public void onMessage(String message)  {
        try {
            //将消息推送给指定用户
            Message msg = JSON.parseObject(message, Message.class);
            //获取 消息接收方的用户名
            String toname = msg.getToname();
            String mess = msg.getMessage();
            Session session = onlineUsers.get(toname);
            String user = (String) this.httpSession.getAttribute("user");
            String msg1 = MessageUtils.getMessage(false, user, mess);
            session.getBasicRemote().sendText(msg1);
        }catch (Exception e) {
            //日志
        }
    }









    /**
     * 断开websocket连接
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        //1.从onlineusers中剔除当前用户的Sessionde对象
        String user = (String) this.httpSession.getAttribute("user");
        onlineUsers.remove(user);

        //2.通知其他所有用户,当前用户已下线
        String message = MessageUtils.getMessage(true, null, getFriends());
        broadcastAllUsers(message);
    }
}
