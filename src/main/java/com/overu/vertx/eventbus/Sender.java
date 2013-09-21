package com.overu.vertx.eventbus;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class Sender extends Verticle {

  @Override
  public void start() {
    vertx.setPeriodic(1000, new Handler<Long>() {

      @Override
      public void handle(Long event) {
        vertx.eventBus().send("ping-address", "ping!", new Handler<Message<String>>() {

          @Override
          public void handle(Message<String> event) {
            System.out.println("Received reply: " + event.body());
          }
        });
      }
    });
  }
}
