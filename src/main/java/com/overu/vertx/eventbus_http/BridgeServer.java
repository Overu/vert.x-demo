package com.overu.vertx.eventbus_http;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.platform.Verticle;

public class BridgeServer extends Verticle {

  Logger logger;

  @Override
  public void start() {
    logger = container.logger();

    HttpServer server = vertx.createHttpServer();

    server.requestHandler(new Handler<HttpServerRequest>() {

      @Override
      public void handle(HttpServerRequest event) {
        System.out.println(event.path());
        if (event.path().equals("/")) {
          System.out.println("index");
          event.response().sendFile("eventbus_http/index.html");
        }

        if (event.path().endsWith("vertxbus.js")) {
          System.out.println("vertxbus");
          event.response().sendFile("eventbus_http/vertxbus.js");
        }
      }
    });

    JsonArray array = new JsonArray();
    array.add(new JsonObject());

    ServerHook hook = new ServerHook(logger);

    SockJSServer sockJSServer = vertx.createSockJSServer(server);
    sockJSServer.setHook(hook);
    sockJSServer.bridge(new JsonObject().putString("prefix", "/eventbus"), array, array);
    server.listen(8080);
  }
}
