package com.github.mcs.arquillian.mdb.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class ExampleMDBBadTest {

    @Resource(mappedName = "ConnectionFactory", name = "ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/queue/example", name = "jms/queue/example")
    private Queue queue;

    @Inject
    private ExampleMessageHandler exampleMessageHandler;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "exampleMDB.war")
                .addPackages(true, ExampleMDB.class.getPackage())
                .addAsWebInfResource("hornetq-jms.xml", "hornetq-jms.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void testOnMessage() throws Exception {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(queue);

        TextMessage textMessage = session.createTextMessage("Hello world!");
        producer.send(textMessage);
        session.close();
        connection.close();

        System.out.println("Here we should have been able to verify the successful MDB execution, " +
                "but we have no way to get informed about the message retrieved from the MDB :-(");
    }

}
