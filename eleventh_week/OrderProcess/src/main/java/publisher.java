import redis.clients.jedis.Jedis;

public class publisher {
    public static void main(String[] args){
        System.out.println("发布者 ");
        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379, 0);// redis服务地址和端口号
            // jr客户端配置监听两个channel
            jr.publish( "news.order", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jr != null) {
                jr.disconnect();
            }
        }
    }
}
