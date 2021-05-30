import java.util.concurrent.*;

public class Homework02_04 {
    static Integer result;

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                result = sum();
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        System.out.println("异步计算结果为："+ result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
