package chapter9;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T>, Comparable<Stack<T>> {

    ArrayList<T> data = new ArrayList<>();

    /****
     * Designate the "end" of the arraylist as the top of stack.
     * 
     * @param d - one piece of data we want to add to the stack
     */
    @Override
    public void push(T d) {
        data.add(d);
    }

    @Override
    public T pop() throws Exception {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.remove(data.size()-1);
    }

    @Override
    public T top() throws Exception {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.get(data.size()-1);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "ArrayStack [data=" + data + ", top()=EMPTY, size()=" + size() + "]";
        } else {
            try {
                return "ArrayStack [data=" + data + ", top()=" + top() + ", size()=" + size() + "]";
            } catch (EmptyStackException ex) {
                return "ArrayStack [data=" + data + ", top()= EMPTY, size()=" + size() + "]";
            } catch (Exception ex) {
                return "Something really bad happened to your stack";
            }
        }
    }

    @Override
    public int compareTo(Stack<T> o) {
        return size()-o.size();
    }

}
