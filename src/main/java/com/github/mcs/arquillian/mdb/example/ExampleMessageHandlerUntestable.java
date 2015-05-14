package com.github.mcs.arquillian.mdb.example;

public class ExampleMessageHandlerUntestable implements ExampleMessageHandler {

    @Override
    public void doSomething(String text) {
        System.out.println("Text was: " + text);
        // doing a lot of work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // irrelevant in this scenario
        }
        System.out.println("Handler execution complete.");
    }

}
