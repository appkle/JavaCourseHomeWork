import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub {
    private Logger logger = LoggerFactory.getLogger(RedisMsgPubSubListener.class);
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("接收到来自" + channel + "的消息" + message);
        System.out.println("处理消息过程中。。。");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消息处理完成");
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        logger.info("onSubscribe: channel[{}], subscribedChannels[{}]", channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        logger.info("channel:{} is been subscribed:{}", channel, subscribedChannels);
    }
}
