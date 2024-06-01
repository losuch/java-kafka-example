/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The ProducerDemoWithCallback class is a Kafka producer that sends messages to
 * a Kafka broker.
 * It uses a callback to handle the response from the broker. If the message is
 * successfully sent,
 * the onCompletion method of the callback is called with a null exception. If
 * an error occurs,
 * the onCompletion method is called with the exception that occurred.
 */
public class ProducerDemoWithCallback {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new ProducerDemoWithCallback().getGreeting());

        // create Kafka producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create producer record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("demo_java", "Hello World");

        // send data
        producer.send(producerRecord, new org.apache.kafka.clients.producer.Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e == null) {
                    log.info("Recived message\n Topic: " + metadata.topic() + "\n Partition: " + metadata.partition()
                            + "\n Offset: " + metadata.offset() + "\n Timestamp: " + metadata.timestamp());
                } else {
                    log.error("Error while producing", e);
                }
            }
        });

        // flush data
        producer.flush();

        // flush and close producer
        producer.close();
    }
}