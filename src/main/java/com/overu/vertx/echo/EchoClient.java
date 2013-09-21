package com.overu.vertx.echo;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;

public class EchoClient extends Verticle {

  @Override
  public void start() {
    // vertx.createNetClient().connect(1234, "localhost", new AsyncResultHandler<NetSocket>() {
    // @Override
    // public void handle(AsyncResult<NetSocket> event) {
    // if (event.succeeded()) {
    // NetSocket result = event.result();
    // result.dataHandler(new Handler<Buffer>() {
    //
    // @Override
    // public void handle(Buffer event) {
    // System.out.println("Net client receiving: " + event);
    // }
    // });
    //
    // for (int i = 0; i < 10; i++) {
    // String str = "hello" + i + "\n";
    // System.out.print("Net client sending: " + str);
    // result.write(new Buffer(str));
    // }
    // } else {
    // event.cause().printStackTrace();
    // }
    // }
    // });
    vertx.createNetClient().connect(1234, "localhost", new Handler<AsyncResult<NetSocket>>() {

      @Override
      public void handle(AsyncResult<NetSocket> event) {
        if (event.succeeded()) {
          NetSocket result = event.result();
          result.endHandler(new Handler<Void>() {

            @Override
            public void handle(Void event) {
              System.out.println("close!");
            }
          });
          result.dataHandler(new Handler<Buffer>() {

            @Override
            public void handle(Buffer event) {
              System.out.println("Net client receiving: " + event);
            }
          });
          for (int i = 0; i < 10; i++) {
            String str = "hello" + i + "\n";
            System.out.println("Net client sending: " + str);
            result.write(str);
          }
        } else {
          event.cause().printStackTrace();
        }
      }
    });
  }
}
