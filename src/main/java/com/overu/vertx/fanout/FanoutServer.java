package com.overu.vertx.fanout;

import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;

import java.util.Set;

public class FanoutServer extends Verticle {

  @Override
  public void start() {
    final Set<String> connections = vertx.sharedData().<String> getSet("conns");

    vertx.createNetServer().connectHandler(new Handler<NetSocket>() {

      @Override
      public void handle(final NetSocket event) {
        connections.add(event.writeHandlerID());
        System.out.print(connections.size() + "----" + event.writeHandlerID() + "\n");
        event.dataHandler(new Handler<Buffer>() {

          @Override
          public void handle(Buffer event) {
            for (String id : connections) {
              vertx.eventBus().publish(id, event);
            }
          }
        });
        event.closeHandler(new VoidHandler() {

          @Override
          public void handle() {
            connections.remove(event.writeHandlerID());
          }
        });

      }
    }).listen(1234);
  }
}
