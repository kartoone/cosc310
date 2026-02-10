package chapter9;

public interface Queue<T> {
    public void enqueue(T data);
    public T dequeue() throws Exception;
    public T front() throws Exception;
    public int size();
    public boolean isEmpty();
}
