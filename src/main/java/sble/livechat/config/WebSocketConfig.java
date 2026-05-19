package sble.livechat.config;

import sble.livechat.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Spring WebSocket 경로 및 CORS 설정 클래스
 * 
 * [참고 및 출처]
 * - 공식 문서: Spring Framework Reference - WebSocket Support Config
 *   (https://docs.spring.spring.io/spring-framework/reference/web/websocket.html)
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatWebSocketHandler chatWebSocketHandler;

    public WebSocketConfig(ChatWebSocketHandler chatWebSocketHandler) {
        this.chatWebSocketHandler = chatWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // /ws/chat 경로로 들어오는 웹소켓 연결을 허용하며, 테스트를 위해 모든 도메인(CORS)을 허용합니다.
        registry.addHandler(chatWebSocketHandler, "/ws/chat")
                .setAllowedOrigins("*");
    }
}

