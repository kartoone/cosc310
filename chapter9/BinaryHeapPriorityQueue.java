package chapter9;

import java.util.Arrays;

public class BinaryHeapPriorityQueue<T> implements PriorityQueue<T> {

    private static class Entry<T> {
        final int priority;
        final T data;

        Entry(int priority, T data) {
            this.priority = priority;
            this.data = data;
        }
    }

    private Entry<T>[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public BinaryHeapPriorityQueue() {
        this.heap = (Entry<T>[]) new Entry[16];
        this.size = 0;
    }

    @Override
    public void enqueue(int priority, T data) {
        ensureCapacity(size + 1);
        heap[size] = new Entry<>(priority, data);
        siftUp(size);
        size++;
    }

    @Override
    public T dequeue() throws Exception {
        if (size == 0) {
            throw new Exception("PriorityQueue is empty");
        }

        T result = heap[0].data;

        size--;
        heap[0] = heap[size];
        heap[size] = null;

        if (size > 0) {
            siftDown(0);
        }

        return result;
    }

    @Override
    public T front() throws Exception {
        if (size == 0) {
            throw new Exception("PriorityQueue is empty");
        }
        return heap[0].data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[i].priority >= heap[parent].priority) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    private void siftDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left >= size) {
                return;
            }

            int smallest = left;
            if (right < size && heap[right].priority < heap[left].priority) {
                smallest = right;
            }

            if (heap[i].priority <= heap[smallest].priority) {
                return;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int a, int b) {
        Entry<T> tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    private void ensureCapacity(int needed) {
        if (needed <= heap.length) {
            return;
        }
        heap = Arrays.copyOf(heap, heap.length * 2);
    }
}
