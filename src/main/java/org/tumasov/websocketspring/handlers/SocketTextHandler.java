package org.tumasov.websocketspring.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage webSocketData) throws Exception {
    String payload = webSocketData.getPayload();
    JsonNode jsonMessage = objectMapper.readTree(payload);
    String message = jsonMessage.get("message").asText();

    if ("bye".equals(message)) {
      session.sendMessage(new TextMessage("bye"));
    } else {
      session.sendMessage(new TextMessage("Yeah, your message has been received: " + message + ", thanks"));
    }
  }
}
