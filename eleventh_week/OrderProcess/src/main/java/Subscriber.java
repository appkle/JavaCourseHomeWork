import redis.clients.jedis.Jedis;

public class Subscriber implements Runnable{
    private RedisMsgPubSubListener redisMsgPubSubListener;
    private String channelName;

    public Subscriber(RedisMsgPubSubListener redisMsgPubSubListener, String channelName) {
        this.redisMsgPubSubListener = redisMsgPubSubListener;
        this.channelName = channelName;
    }

    public void run() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("127.0.0.1", 6379, 0);// redis服务地址和端口号
            jedis.subscribe(redisMsgPubSubListener, channelName);// 通过jedis.subscribe()方法去订阅，入参是1.订阅者、2.频道名称
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("频道订阅失败：%s",e));
        } finally {
            if (null != jedis)
                jedis.close();
        }
    }
}
