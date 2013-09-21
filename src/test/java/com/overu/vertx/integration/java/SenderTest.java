package com.overu.vertx.integration.java;

import com.overu.vertx.eventbus.Receiver;

import static org.vertx.testtools.VertxAssert.assertNotNull;
import static org.vertx.testtools.VertxAssert.assertTrue;
import static org.vertx.testtools.VertxAssert.testComplete;

import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.testtools.TestVerticle;

public class SenderTest extends TestVerticle {

  @Override
  public void start() {
    initialize();
    container.deployModule(System.getProperty("vertx.modulename"), new AsyncResultHandler<String>() {
      @Override
      public void handle(AsyncResult<String> asyncResult) {
        assertTrue(asyncResult.succeeded());
        assertNotNull("deploymentID should not be null", asyncResult.result());
        startTests();
      }
    });
  }

  @Test
  public void testPing() {
    container.logger().info("in testPing()");
    // vertx.setPeriodic(1000, new Handler<Long>() {
    //
    // @Override
    // public void handle(Long event) {
    vertx.eventBus().send("ping-address", "ping!", new Handler<Message<String>>() {

      @Override
      public void handle(Message<String> event) {
        System.out.println("Received reply: " + event.body());
        testComplete();
      }
    });
    // }
    // });
    // vertx.eventBus().send("ping-address", "ping!", new Handler<Message<String>>() {
    // @Override
    // public void handle(Message<String> reply) {
    // assertEquals("pong!", reply.body());
    //
    // /*
    // * If we get here, the test is complete You must always call `testComplete()` at the end. Remember that testing is *asynchronous* so
    // * we cannot assume the test is complete by the time the test method has finished executing like in standard synchronous tests
    // */
    // testComplete();
    // }
    // });
  }

  @Test
  public void testSomethingElse() {
    // Whatever
    testComplete();
  }

}
