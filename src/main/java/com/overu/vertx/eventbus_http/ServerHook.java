package com.overu.vertx.eventbus_http;

import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.sockjs.EventBusBridgeHook;
import org.vertx.java.core.sockjs.SockJSSocket;

public class ServerHook implements EventBusBridgeHook {

  Logger logger;

  public ServerHook(Logger logger) {
    this.logger = logger;
  }

  @Override
  public void handlePostRegister(SockJSSocket sock, String address) {
    logger.info("handlePostRegister, sock = " + sock + ", address = " + address);
  }

  @Override
  public boolean handlePreRegister(SockJSSocket sock, String address) {
    logger.info("handlePreRegister, sock = " + sock + ", address = " + address);
    return true;
  }

  @Override
  public boolean handleSendOrPub(SockJSSocket sock, boolean send, JsonObject msg, String address) {
    logger.info("handleSendOrPub, sock = " + sock + ", send = " + send + ", address = " + address);
    logger.info(msg);
    return true;
  }

  @Override
  public void handleSocketClosed(SockJSSocket sock) {
    logger.info("handleSocketClosed, sock = " + sock);
  }

  @Override
  public boolean handleUnregister(SockJSSocket sock, String address) {
    logger.info("handleUnregister, sock = " + sock + ", address = " + address);
    return true;
  }

}
