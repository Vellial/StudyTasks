import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueue {
    private AtomicInteger size;
    private Queue<Integer> queue;

    public BlockingQueue(int size) {
        this.size = new AtomicInteger(size);
        queue = new LinkedList<>();
    }

    public synchronized void enqueue(Integer element) throws InterruptedException {
        while (queue.size() >= size.get()) {
            wait();
        }
        queue.add(element);
        size.incrementAndGet();
        notifyAll();
    }

    public synchronized Integer dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Integer element = queue.poll();
        size.decrementAndGet();
        notifyAll();
        return element;

    }

    public int size() {
        return size.get();
    }
}
