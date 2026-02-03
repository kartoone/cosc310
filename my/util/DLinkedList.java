package my.util;

public class DLinkedList<T> {

    private DNode<T> head;
    private DNode<T> tail;
    private int size;

    public DLinkedList() {

    }
    
    public void addAfter(DNode<T> node, T data) {
        size++;
    }

    public void add(T data) {
        DNode<T> newnode = new DNode<>(tail, null, data);
        if (size==0) {
            head=newnode;
        } else {
            tail.setNext(newnode);
            newnode.setPrev(tail);
        }
        // unconditionally, we have a new tail
        // AND our size has increased by 1
        size++;        
        tail = newnode;
    }



}