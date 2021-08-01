package demo;

import core.KmqBroker;
import core.KmqConsumer;
import core.KmqMessage;
import core.KmqProducer;
import lombok.SneakyThrows;

public class KmqDemo {

    @SneakyThrows
    public static void main(String[] args) {
        String topic = "kk.test";
        KmqBroker broker = new KmqBroker();
        broker.createTopic(topic);

        KmqConsumer consumer1 = broker.createConsumer();
        consumer1.subscribe(topic);
        final boolean[] flag = new boolean[1];
        flag[0] = true;
        new Thread(() -> {
            while (flag[0]) {
                KmqMessage<Order> message = consumer1.poll(1000);
                if(null != message) {
                    System.out.println(message.getBody());
                }
            }
            System.out.println("消费者1——程序退出。");
        }).start();

        //创建两个生产者
        KmqProducer producer1 = broker.createProducer();
        KmqProducer producer2 = broker.createProducer();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                Order order = new Order(10L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
                producer1.send(topic, new KmqMessage(null, order));
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                Order order = new Order(20L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
                producer2.send(topic, new KmqMessage(null, order));
            }
        }).start();
        Thread.sleep(500);

        //创建第二个消费者
        KmqConsumer consumer2 = broker.createConsumer();
        consumer2.subscribe(topic);
        new Thread(() -> {
            while (flag[0]) {
                KmqMessage<Order> message = consumer2.poll(1000);
                if(null != message) {
                    System.out.println(message.getBody());
                }
            }
            System.out.println("消费者2——程序退出。");
        }).start();

        System.out.println("点击任何键，发送一条消息；点击q或e，退出程序。");
        while (true) {
            char c = (char) System.in.read();
//            if(c > 20) {
//                System.out.println(c);
//                producer1.send(topic, new KmqMessage(null, new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)));
//            }

            if( c == 'q' || c == 'e') break;
        }

        flag[0] = false;
    }
}
