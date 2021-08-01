package core;

public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        this.broker.setComsumerOffset(kmq, this.hashCode());
    }

    public KmqMessage<T> poll() {
        return kmq.poll(this.hashCode());
    }

    public KmqMessage<T> poll(long timeout) {
        KmqMessage<T> message =  kmq.poll(this.hashCode(), timeout);
        if (message != null) {
            commitSync();
        }
        return message;
    }

    private void commitSync(){
        this.broker.commitSync(kmq, this.hashCode());
    }
}
