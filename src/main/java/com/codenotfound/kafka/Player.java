package com.codenotfound.kafka;

import com.codenotfound.kafka.consumer.Receiver;
import com.codenotfound.kafka.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by v.bekarev on 28.10.2018.
 */
@Component
public class Player {
    private static final String HELLOWORLD_TOPIC = "helloworld.t";

    Sender sender;
    Receiver receiver;
    Boolean isSender = Boolean.TRUE;

    @Autowired
    public Player(Sender sender,
                  Receiver receiver) {
        this.sender = sender;
        this.receiver = receiver;
        play();
    }

    private void play() {
        if (isSender) {
            sender.send(HELLOWORLD_TOPIC, "Hello Spring Kafka!");
        } else {
            try {
                receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException exc) {
                //
            }
        }
    }
}
