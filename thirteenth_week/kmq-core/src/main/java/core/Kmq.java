package core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Kmq {
    private String topic;
    private int capacity;
    private MessageQueue<KmqMessage> queue;
    private AtomicInteger currWritePos;
    private ConcurrentHashMap<Integer, Integer> offsets;

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new MessageQueue(capacity);
        this.currWritePos = new AtomicInteger(0);
        this.offsets = new ConcurrentHashMap<Integer, Integer>();
    }

    public void setConsumerOffset(int consumerHashCode){
        offsets.put(consumerHashCode, 0);
    }

    public void send(KmqMessage message) {
        queue.offer(message);
        currWritePos.incrementAndGet();
    }

    public KmqMessage poll(int consumerHashCode) {
        Integer consumerOffset = offsets.get(consumerHashCode);
        return queue.poll(consumerOffset);
    }

    public KmqMessage poll(int consumerHashcode, long timeout) {
        Integer consumerOffset = offsets.get(consumerHashcode);
        return queue.poll(consumerOffset, timeout, TimeUnit.MILLISECONDS);
    }

    public void commitSync(int consumerHashCode){
        Integer offset = offsets.get(consumerHashCode);
        offsets.put(consumerHashCode, offset+1);
    }
}
