import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscriberClient {
    public static void main(String[] args) throws InterruptedException {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        RedisMsgPubSubListener redisMsgPubSubListener = new RedisMsgPubSubListener();
        //订阅频道
        Subscriber subscriber = new Subscriber(redisMsgPubSubListener, "news.order");
        executorService.submit(subscriber);
        executorService.shutdown();

        Thread.sleep(15000);
        redisMsgPubSubListener.unsubscribe("news.order");
    }
}
