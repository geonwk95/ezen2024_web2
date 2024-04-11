package ezenweb.controller;

import jakarta.websocket.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
public class ChatSocket extends TextWebSocketHandler {

    // 0. 접속 성공한 session 들을 모아두기 ( 접속명단 )
    private List<WebSocketSession> webSocketSessionList = new Vector<>(); // ArrayList vs Vector

    // 1. 클라이언트의 소켓의 접속이 성공일때 ( session : 클라이언트의 소켓 정보 )
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("session = " + session);
        // 1. 접속한 세션정보를 리스트에 담기
        webSocketSessionList.add( session );
        System.out.println("접속명단 : "+webSocketSessionList);
    }

    // 2. 클라이언트 으로부터 메세지를 받았을때 ( session : 메세지를 보낸 클라이언트 소켓 , message : 클라이언트에게 받은 메세지 )
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("session = " + session + ", message = " + message);
        System.out.println("message.getPayload() = " + message.getPayload());

        // 1. 접속명단의 클라이언트소켓 들에게 메세지 보내기
        for( WebSocketSession 클라이언트소켓 : webSocketSessionList ){
            클라이언트소켓.sendMessage( message );
        }
    }
    // 3. 클라이언트 소켓과 접속 종료되었을때 ( session : 접속이 종료된 클라이언트 소켓 정보 )
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionList.remove( session );
    }
}
