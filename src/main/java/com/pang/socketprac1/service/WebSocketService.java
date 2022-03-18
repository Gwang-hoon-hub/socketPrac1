package com.pang.socketprac1.service;

import com.pang.socketprac1.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;

    public void notifyFrontEnd(final String message){
        // 응답 메시지 객체 생성
        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendGlobalNotification();
        simpMessagingTemplate.convertAndSend("/sub/message", responseMessage);
    }

    public void notifyUser(final String id, final String message){
        // 응답 메시지 객체 생성
        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendPrivateNotification(id);
        simpMessagingTemplate.convertAndSendToUser(id, "/sub/private-message", responseMessage);
    }
}
