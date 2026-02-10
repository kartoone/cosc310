package chapter9;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {

    // let's designate the end of the arraylist as the rear of the queue
    // and the beginning of the arraylist as the front of the queue
    private ArrayList<T> data = new ArrayList<>();

    @Override
    public void enqueue(T d) {
        data.add(d);
    }

    @Override
    public T dequeue() throws Exception {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.removeFirst();
    }

    @Override
    public T front() throws Exception {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.getFirst();
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
