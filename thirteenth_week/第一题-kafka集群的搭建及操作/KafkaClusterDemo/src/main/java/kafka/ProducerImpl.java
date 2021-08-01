package kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerImpl implements Producer {
    private Properties properties;
    private KafkaProducer<String, String> producer;
    private final String topic = "test32";

    public ProducerImpl(){
        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9003");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
    }

    @Override
    public void send(Order order) {
        ProducerRecord record = new ProducerRecord(topic, order.getId().toString(), JSON.toJSONString(order));
        producer.send(record); //异步发送
    }

    @Override
    public void close() {
        producer.close();
    }
}
