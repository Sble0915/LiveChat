package sble.livechat.network;

/**
 * 서버가 클라이언트와 통신하기 위한 규격을 정의한 인터페이스
 * 
 * [참고 및 출처]
 * - 디자인 패턴: GoF의 전략 패턴(Strategy Pattern) 개념 적용
 * - 본 코드는 인프라 기술(Web/Java)에 의존하지 않는 독립적인 핵심 로직 설계를 위해 작성되었습니다.
 */
public interface ChatClientConnection {
    
    /**
     * 연결된 클라이언트에게 실시간 메시지를 전송합니다.
     */
    void sendMessage(String message) throws Exception;
    
    /**
     * 현재 네트워크 연결이 정상적으로 열려있는지 확인합니다.
     */
    boolean isOpen();
    
    /**
     * 클라이언트 연결의 고유 식별자(ID)를 반환합니다.
     */
    String getId();
}

