package sble.livechat.manager;

import sble.livechat.network.ChatClientConnection;
import org.springframework.stereotype.Component;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 채팅방 회원 관리 및 메시지 브로드캐스트를 담당하는 관리자 클래스
 * 
 * [참고 및 출처]
 * - 동시성 제어: 멀티스레드 환경에서 안전하게 세션을 추가/제거하기 위해 CopyOnWriteArraySet을 사용했습니다.
 * - 디자인 패턴: 의존성 주입(DI) 및 인터페이스 기반 설계 패턴 적용 (GoF Strategy Pattern 구조)
 */
@Component
public class ChatManager {
    private final CopyOnWriteArraySet<ChatClientConnection> clients = new CopyOnWriteArraySet<>();

    public void addClient(ChatClientConnection client) {
        clients.add(client);
        System.out.println("[서버 로그] 클라이언트 입장 -> 현재 접속자 수: " + clients.size());
    }

    public void removeClient(ChatClientConnection client) {
        clients.remove(client);
        System.out.println("[서버 로그] 클라이언트 퇴장 -> 현재 접속자 수: " + clients.size());
    }

    public void broadcast(String message) {
        System.out.println("[서버 브로드캐스트] " + message);
        for (ChatClientConnection client : clients) {
            try {
                if (client.isOpen()) {
                    client.sendMessage(message);
                }
            } catch (Exception e) {
                System.err.println("메시지 전송 실패 (ID: " + client.getId() + "): " + e.getMessage());
                removeClient(client);
            }
        }
    }
}

