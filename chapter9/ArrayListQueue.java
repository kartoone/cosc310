package chapter9;

import java.util.ArrayList;

public class ArrayListQueue<T> implements Queue<T> {

    // Circular-buffer queue stored in an ArrayList.
    private ArrayList<T> buffer;
    private int head; // index of current front
    private int tail; // index of next insertion position
    private int size;

    public ArrayListQueue() {
        buffer = new ArrayList<>(16);
        for (int i = 0; i < 16; i++) buffer.add(null);
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void enqueue(T item) {
        // TODO
    }

    @Override
    public T dequeue() throws Exception {
        // TODO
        return null;
    }

    @Override
    public T front() throws Exception {
        // TODO
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int needed) {
        // TODO: if needed > buffer.size(), double capacity and re-center head at 0
    }
}
