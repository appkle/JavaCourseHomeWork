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
        System.out.println(String.format( "订阅成功 频道：%s；频道数：%d。", channel, subscribedChannels));
    }

    @Override
    public void onUnsubscribe(String channelName, int subscribedChannels) {
        System.out.println(String.format( "取消订阅 频道：%s；频道数：%d。",channelName , subscribedChannels));
    }
}
