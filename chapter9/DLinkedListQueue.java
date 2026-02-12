package chapter9;

import my.util.DLinkedList;
import my.util.DNode;

public class DLinkedListQueue<T> implements Queue<T> {

    private final DLinkedList<T> list;

    public DLinkedListQueue() {
        list = new DLinkedList<>();
    }

    @Override
    public void enqueue(T item) {
        // TODO (enqueue at tail)
    }

    @Override
    public T dequeue() throws Exception {
        // TODO (dequeue from head using removeFirst())
        return null;
    }

    @Override
    public T front() throws Exception {
        // TODO (peek at head)
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
