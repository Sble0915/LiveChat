package sble.livechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 실시간 채팅 서버의 시작점 (메인 엔트리포인트)
 * 
 * [참고 및 출처]
 * - Spring Boot Reference Documentation - 구조 표준 가이드 준수
 */
@SpringBootApplication
public class LiveChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiveChatApplication.class, args);
    }
}

