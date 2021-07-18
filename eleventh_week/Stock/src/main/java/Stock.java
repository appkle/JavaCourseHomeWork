import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Stock {
    private int amount;
    private Jedis jedis;
    private static String key = "stock";

    public Stock(){
        jedis = new Jedis("localhost", 6379);
    }

    public void setStock(int amount){
        jedis.set(key, String.valueOf(amount));
        jedis.close();
    }

    public String getStock(){
        String stock = jedis.get(key);
        jedis.close();
        return stock;
    }

    public void decreaseStock(int amount){
        String script;
        Reader r = new InputStreamReader(Stock.class.getResourceAsStream("/decreaseStock.lua"));
        try {
            script = CharStreams.toString(r);
            Object result = jedis.eval(script, Lists.newArrayList("stock"), Lists.newArrayList(String.valueOf(amount)));
            if ((Long)result == -1){
                System.out.println("减库存失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jedis.close();
    }

    public static void main(String[] args) throws InterruptedException {
        final Stock stock = new Stock();
        stock.setStock(100);

        int clientcount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        for (int i = 0; i < clientcount; i++) {
            executorService.execute(() -> {
                Stock stock1 = new Stock();
                stock1.decreaseStock(30);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        Stock stock2 = new Stock();
        String result = stock2.getStock();
        System.out.println(result);
        executorService.shutdown();
    }
}
