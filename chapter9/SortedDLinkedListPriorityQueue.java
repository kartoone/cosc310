package chapter9;

import my.util.DLinkedList;
import my.util.DNode;

public class SortedDLinkedListPriorityQueue<T> implements PriorityQueue<T> {

    private static class Entry<T> implements Comparable<Entry<T>> {
        final int priority;
        final T data;
        Entry(int priority, T data) {
            this.priority = priority;
            this.data = data;
        }

        @Override
        public int compareTo(Entry<T> o) {
            return o.priority - this.priority;
        }
    }

    private final DLinkedList<Entry<T>> list;

    public SortedDLinkedListPriorityQueue() {
        list = new DLinkedList<>();
    }

    @Override
    public void enqueue(int priority, T data) {
        Entry<T> newentry = new Entry<>(priority, data);
        if (isEmpty()) {
            list.add(newentry);
            return;
        }
        
        // Algorithm: make sure first item in DLInkedList is always highest priority
        // Start with first item and traverse the list until you get to a higher priority item
        // Insert the new item before the one you just found
        DNode<Entry<T>> curr = list.get(0);
        while (curr!=null) {
            if (priority<curr.getData().priority) {
                list.addBefore(curr, newentry);
                break;
            }
            curr = curr.getNext();
        }

        // if you get to the end of the list, add it at the end
        if (curr==null) {
            list.add(newentry); 
        }

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
