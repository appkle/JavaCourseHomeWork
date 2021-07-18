import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Lock {
    private String uuid;
    private Jedis jedis;
    private long timeout = 999999; //获取锁的超时时间

    public Lock(){
        uuid = java.util.UUID.randomUUID().toString();
        jedis = new Jedis("localhost", 6379);
    }

    public boolean lock() {
        Long start = System.currentTimeMillis();
        for(;;){
            String result = jedis.set("lock_key", uuid, "nx", "px", 10000);
            if ("OK".equals(result)){
                return true;
            }
            long l = System.currentTimeMillis() - start;
            if(l >= timeout){
                return false;
            }
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock(){
        String luaStr;
        Reader r = new InputStreamReader(Lock.class.getResourceAsStream("/unlock.lua"));
        try {
            luaStr = CharStreams.toString(r);
            jedis.eval(luaStr, Lists.newArrayList("lock_key"),
                    Lists.newArrayList(uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jedis.close();
    }
}
