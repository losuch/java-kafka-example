package org.example.wikimedia;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

public class WikimediaProducer {

    public static void main(String[] args) throws InterruptedException {
        String bootstrapServer = "127.0.0.1:9092";

        // create Kafka producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootstrapServer);
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "wikimedia.change";

        EventHandler handler = new WikimediaChangeHandler(producer, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        // create event source
        EventSource.Builder builder = new EventSource.Builder(handler, URI.create(url));

        // create event source
        EventSource eventSource = builder.build();

        // start in another thhread

        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
