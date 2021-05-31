import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Homework02_5 {
    static Integer result;

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        Thread thread = new Thread(()->result = sum());
        thread.start();
        thread.join();
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
