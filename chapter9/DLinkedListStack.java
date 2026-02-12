package chapter9;

import my.util.DLinkedList;
import my.util.DNode;

public class DLinkedListStack<T> implements Stack<T> {

    private final DLinkedList<T> list;

    public DLinkedListStack() {
        list = new DLinkedList<>();
    }

    @Override
    public void push(T item) {
        // TODO (use tail as the top)
    }

    @Override
    public T pop() throws Exception {
        // TODO
        return null;
    }

    @Override
    public T top() throws Exception {
        // TODO
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
