package com.overu.vertx.echo;

import org.vertx.java.core.Handler;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.core.streams.Pump;
import org.vertx.java.platform.Verticle;

public class EchoServer extends Verticle {

  @Override
  public void start() {
    vertx.createNetServer().connectHandler(new Handler<NetSocket>() {
      @Override
      public void handle(NetSocket event) {
        System.out.println("connect");
        Pump.createPump(event, event).start();
      }
    }).listen(1234);

  }
}
