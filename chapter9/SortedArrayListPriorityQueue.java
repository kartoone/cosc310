package chapter9;

import java.util.ArrayList;

public class SortedArrayListPriorityQueue<T> implements PriorityQueue<T> {

    private static class Entry<T> implements Comparable<Entry<T>> {
        final int priority;
        final T data;
        Entry(int priority, T data) {
            this.priority = priority;
            this.data = data;
        }

        @Override
        public int compareTo(Entry<T> o) {
            return this.priority - o.priority;
        }
    }

    private final ArrayList<Entry<T>> list;

    public SortedArrayListPriorityQueue() {
        list = new ArrayList<>();
    }

    @Override
    public void enqueue(int priority, T data) {
        // TODO: insert so list is sorted by priority ASC (lower number is higher priority)
        list.add(new Entry<>(priority, data));
        list.sort(null);
    }

    @Override
    public T dequeue() throws Exception {
        // TODO: remove index 0
        return list.remove(0).data;
    }

    @Override
    public T front() throws Exception {
        // TODO: return index 0
        return list.getFirst().data;
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
