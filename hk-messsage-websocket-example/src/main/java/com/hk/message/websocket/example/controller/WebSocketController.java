package com.hk.message.websocket.example.controller;

import com.hk.message.websocket.example.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: sjq-278
 * @date: 2018-09-21 12:32
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/chat")
    @ResponseBody
    public String handlerChat(Message message) {
        messagingTemplate.convertAndSend("/queue/notifications", message.getName());
        return "/chat";
    }

    @MessageMapping("chat2")
    public void handlerChat2(Message message) {
        messagingTemplate.convertAndSend("/queue/notifications", message.getName());
    }
}
