package com.example.testeconsumidor2;

import javax.jms.*;
import javax.naming.InitialContext;

public class TesteProdutor2 {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("financeiro");
        MessageProducer producer = session.createProducer(fila);

        for(int i = 0; i < 1000; i ++) {
            Message message = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
            producer.send(message);
        }

        session.close();
        connection.close();
        context.close();
    }
}
