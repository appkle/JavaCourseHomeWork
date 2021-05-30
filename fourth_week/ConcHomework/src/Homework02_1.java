import java.util.concurrent.*;

public class Homework02_1 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        executor.shutdown();
        int result = 0;
        try {
            result =  future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
