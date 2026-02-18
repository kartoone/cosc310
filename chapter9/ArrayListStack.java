package chapter9;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<T> implements Stack<T> {

    private final ArrayList<T> data;

    public ArrayListStack() {
        data = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        data.add(item);
    }

    @Override
    public T pop() throws Exception {
        // TODO
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.removeLast();
    }

    @Override
    public T top() throws Exception {
        // TODO
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.getLast();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
