package com.overu.vertx.eventbus;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class Receiver extends Verticle {

  @Override
  public void start() {
    vertx.eventBus().registerHandler("ping-address", new Handler<Message<String>>() {
      @Override
      public void handle(Message<String> event) {
        System.out.println("Received message: " + event.body());
        event.reply("pond!!!");
      }
    });
  }

}
