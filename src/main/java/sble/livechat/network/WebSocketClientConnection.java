package sble.livechat.network;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 웹 브라우저(WebSocket) 테스트를 위한 통신 구현체
 * 
 * [참고 및 출처]
 * - Spring Framework Reference: WebSocket API 표준 명세 준수
 *   (https://docs.spring.spring.io/spring-framework/reference/web/websocket.html)
 */
public class WebSocketClientConnection implements ChatClientConnection {
    private final WebSocketSession session;

    public WebSocketClientConnection(WebSocketSession session) {
        this.session = session;
    }

    @Override
    public void sendMessage(String message) throws Exception {
        if (isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    @Override
    public boolean isOpen() {
        return session != null && session.isOpen();
    }

    @Override
    public String getId() {
        return session.getId();
    }
}

