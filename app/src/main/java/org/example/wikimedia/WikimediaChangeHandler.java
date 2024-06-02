package org.example.wikimedia;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikimediaChangeHandler implements EventHandler {

    private final Logger log = LoggerFactory.getLogger(WikimediaChangeHandler.class);

    private KafkaProducer<String, String> producer;
    private String topic;

    public WikimediaChangeHandler(KafkaProducer<String, String> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        log.info("Connection opened");
    }

    @Override
    public void onClosed() throws Exception {
        log.info("Connection closed");
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) {
        log.info("Event: " + event + ", Message: " + messageEvent.getData());

        // send message to kafka asynchrounously
        producer.send(new ProducerRecord<String, String>(topic, messageEvent.getData()));
    }

    @Override
    public void onComment(String comment) throws Exception {
        log.info("Comment: " + comment);
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error: " + t.getMessage());
    }

}
