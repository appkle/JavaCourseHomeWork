package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import com.alibaba.fastjson.JSON;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerImpl implements Consumer {
    private final String topic = "test32";
    private Properties properties;
    private KafkaConsumer<String, String> consumer;

    public ConsumerImpl(){
        properties = new Properties();
        properties.put("group.id", "my-consumer");
        properties.put("bootstrap.servers", "localhost:9001");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer(properties);
    }

    @Override
    public void consumeOrder() {
        consumer.subscribe(Collections.singletonList(topic));

        while (true){
            ConsumerRecords<String, String> poll =  consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> o : poll){
                ConsumerRecord<String, String> record = (ConsumerRecord) o;
                Order order = JSON.parseObject(record.value(), Order.class);
                System.out.println(" order = " + order);
            }
        }
    }

    @Override
    public void close() {
        consumer.close();
    }
}
