package chapter9;

import my.util.DLinkedList;
import my.util.DNode;

public class SortedDLinkedListPriorityQueue<T> implements PriorityQueue<T> {

    private static class Entry<T> {
        final int priority;
        final T data;
        Entry(int priority, T data) {
            this.priority = priority;
            this.data = data;
        }
    }

    private final DLinkedList<Entry<T>> list;

    public SortedDLinkedListPriorityQueue() {
        list = new DLinkedList<>();
    }

    @Override
    public void enqueue(int priority, T data) {
        // TODO: find insertion point and insert so list is sorted by priority ASC
    }

    @Override
    public T dequeue() throws Exception {
        // TODO: removeFirst()
        return null;
    }

    @Override
    public T front() throws Exception {
        // TODO: peek head
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
