package chapter9;

public interface Stack<T> {

    public void push(T data);
    public T pop() throws Exception;
    public T top() throws Exception;
    public int size();
    public boolean isEmpty();

}
