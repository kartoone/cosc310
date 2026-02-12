package chapter9;

import java.util.ArrayList;

public class ArrayListStack<T> implements Stack<T> {

    private final ArrayList<T> data;

    public ArrayListStack() {
        data = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        // TODO
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
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
