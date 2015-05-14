package com.github.mcs.arquillian.mdb.example;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Alternative
@ApplicationScoped
public class ExampleMessageHandlerTestable implements ExampleMessageHandler {

    private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();


    public void doSomething(String text) {
        queue.add(text);
    }

    public String poll(int secondsUntilInterrupt) throws InterruptedException {
        return queue.poll(secondsUntilInterrupt, TimeUnit.SECONDS);
    }

}
