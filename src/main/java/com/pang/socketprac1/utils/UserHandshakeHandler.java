package com.pang.socketprac1.utils;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

// 여기서 알림을 받거나 주는 사람을 확인해야한다. ---> 토큰으로 확인
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 유저 생성하기
        final String randomId = UUID.randomUUID().toString();
        LOG.info("User with ID '{}' opened the page : ", randomId);
        return new UserPrincipal(randomId);
    }
}
