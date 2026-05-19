package sble.livechat.handler;

import sble.livechat.manager.ChatManager;
import sble.livechat.network.WebSocketClientConnection;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 웹소켓 이벤트를 처리하고 ChatManager와 상호작용하는 핸들러
 * 
 * [참고 및 출처]
 * - Baeldung: "WebSockets with Spring" 로직 구조 참고
 *   (https://www.baeldung.com/spring-websockets-send-message-to-user)
 * - 동시성 맵: 세션 ID와 구현체 매핑을 안전하게 처리하기 위해 ConcurrentHashMap을 사용했습니다.
 */
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ChatManager chatManager;
    private final ConcurrentHashMap<String, WebSocketClientConnection> connectionMap = new ConcurrentHashMap<>();

    public ChatWebSocketHandler(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        WebSocketClientConnection connection = new WebSocketClientConnection(session);
        connectionMap.put(session.getId(), connection);
        chatManager.addClient(connection);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        chatManager.broadcast(payload);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WebSocketClientConnection connection = connectionMap.remove(session.getId());
        if (connection != null) {
            chatManager.removeClient(connection);
        }
    }
}

