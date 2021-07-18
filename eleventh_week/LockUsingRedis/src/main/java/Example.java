import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        int clientcount = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        for (int i = 0; i < clientcount; i++) {
            executorService.execute(() -> {
                Lock redisLock = new Lock();
                redisLock.lock();
                count++;
                redisLock.unlock();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("count = " + count);
        executorService.shutdown();
    }
}
