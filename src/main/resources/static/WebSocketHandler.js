'use strict';

class WebSocketHandler {
  constructor() {
    this.webSocket = null;
    this.onMessageReceived = null;
    this.isConnected = false;
  }

  connect(address, onMessageReceived) {
    if (!this.isConnected) {
      this.webSocket = new WebSocket('ws://' + address);
      this.webSocket.onmessage = (this.onMessageReceived = onMessageReceived);
      this.isConnected = true;
      console.log("WebSocket has been initialized and connected...");
    }
  }

  send(message) {
    if (this.isConnected) {
      this.webSocket.send(JSON.stringify({'message': message}));
    }
  }

  disconnect() {
    if (this.isConnected) {
      this.webSocket.close();
      this.onMessageReceived = null;
      this.isConnected = false;
      console.log("WebSocket has been disconnected...");
    }
  }
}