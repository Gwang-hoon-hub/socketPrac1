package com.pang.socketprac1.config;

import com.pang.socketprac1.utils.UserHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // stomp websocket의 연결 endpoint는 /ws-stomp 로 설정한다.
        registry.addEndpoint("/ws-stomp").setHandshakeHandler(new UserHandshakeHandler()).withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // 메새지를 구독하는 요청의 prefix는 /sub로 시작한다.
        config.enableSimpleBroker("/sub"); // /topic ,
        // 메세지를 발행하는 요청의 prefix는 /pub으로 시작하고 설정
        config.setApplicationDestinationPrefixes("/pub");
    }

}
