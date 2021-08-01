package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue<T> {
    private List<T> topic;
    private final Lock lock = new ReentrantLock();
    private final Condition notReadFinish = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public MessageQueue(int capacity){
         this.topic = new ArrayList<>(capacity);
    }

    public void offer(T message){
        lock.lock();
        topic.add(message);
        notEmpty.signalAll();
        notReadFinish.signalAll();
        lock.unlock();
    }

    public T poll(int offset) {
        lock.lock();
        try {
            if(topic.size() == 0){
                notEmpty.await();
            }
            else if(topic.size() == offset){
                notReadFinish.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return topic.get(offset);
    }

    public T poll(int offset, long timeout, TimeUnit unit){
        long nanos = unit.toNanos(timeout);
        lock.lock();
        try {
            while (topic.size() == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            while (topic.size() == offset) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = notReadFinish.awaitNanos(nanos);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return topic.get(offset);
    }
}