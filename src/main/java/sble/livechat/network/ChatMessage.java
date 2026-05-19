package sble.livechat.network;

/**
 * 실시간 채팅에서 주고받는 데이터의 구조를 정의한 도메인 클래스
 * 
 * [참고 및 출처]
 * - 자바 표준 관례: 데이터 전달 객체(DTO) 패턴 및 캡슐화 원칙 준수
 */
public class ChatMessage {

    // 메시지의 성격을 구분하기 위한 열거형(Enum)
    public enum MessageType {
        ENTER, // 채팅방 입장
        TALK,  // 일반 대화
        QUIT   // 채팅방 퇴장
    }

    private MessageType type;
    private String sender;
    private String message;

    // 기본 생성자 (JSON 직렬화/역직렬화 시 필수)
    public ChatMessage() {
    }

    public ChatMessage(MessageType type, String sender, String message) {
        this.type = type;
        this.sender = sender;
        this.message = message;
    }

    // Getter 및 Setter 메서드 (데이터 캡슐화)
    public MessageType getType() { return type; }
    public void setType(MessageType type) { this.type = type; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

