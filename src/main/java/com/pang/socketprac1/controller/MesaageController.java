package com.pang.socketprac1.controller;

import com.pang.socketprac1.dto.Message;
import com.pang.socketprac1.dto.ResponseMessage;
import com.pang.socketprac1.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MesaageController {

    private final NotificationService notificationService;

    @MessageMapping("/message")
    @SendTo("/sub/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendGlobalNotification();
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/sub/private-message")
    public ResponseMessage getPrivateMessage(Message message,
                                             Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendPrivateNotification(principal.getName());
        return new ResponseMessage(HtmlUtils.htmlEscape(
                "유저에게 1:1로 알람주기" + principal.getName() + " : " + message.getMessageContent()
        ));
    }

}
