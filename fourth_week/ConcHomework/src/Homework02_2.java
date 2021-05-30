import java.util.concurrent.*;

public class Homework02_2 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Result result = new Result();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Result> future = executor.submit(new Task(result), result);
        executor.shutdown();
        try {
            result =  future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+ result.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class Result{
        int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
    static class Task implements Runnable{
        Result r;
        Task(Result result){
            this.r = result;
        }
        @Override
        public void run() {
             int value = sum();
             r.setResult(value);
        }
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
