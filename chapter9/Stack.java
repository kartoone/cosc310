package chapter9;

public interface Stack<T> {

    public void push(T data);
    public T pop();
    public T top();
    public int size();
    public boolean isEmpty();

}
