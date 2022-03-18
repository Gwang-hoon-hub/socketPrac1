package com.pang.socketprac1.service;

import com.pang.socketprac1.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    // 전역 알림
    public void sendGlobalNotification(){
        ResponseMessage message = new ResponseMessage("Global Notification");
        messagingTemplate.convertAndSend("/sub/global-notification", message);
    }
    
    // 개인 알림
    public void sendPrivateNotification(final String id){
        ResponseMessage message = new ResponseMessage("Private Notification");
        messagingTemplate.convertAndSendToUser(id ,"/sub/private-notification", message);
    }
}
