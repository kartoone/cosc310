package my.util;

public class DLinkedList<T> {

    private DNode<T> head;
    private DNode<T> tail;
    private int size;

    public DLinkedList() {

    }
    
    // need to break the connection between node and whatever its current "next" is
    public void addAfter(DNode<T> node, T data) {
        if (node==tail) {
            add(data);          // already adds at the tail
        } else if (node==null) {
            addFirst(data);     // already adds at the head
        } else {
            // normal case where we are not adding at the beginning or the end
            // the constructor alone lets us set the prev and next for the new node
            DNode<T> newnode = new DNode<>(node, node.getNext(), data);                

            // But we need to update the prev on the node we are being added before
            node.getNext().setPrev(newnode);

            // And we need to update the next on the node we are being added after
            node.setNext(newnode);
            size++;
        }
    }

    public DNode<T> addFirst(T data) {
        DNode<T> newnode = new DNode<>(null, head, data);
        if (size==0) {
            tail=newnode;
        } else {
            head.setPrev(newnode);
            newnode.setNext(head);
        }
        // unconditionally, we have a new tail
        // AND our size has increased by 1
        size++;        
        head = newnode;
        return newnode;
    }

    public DNode<T> add(T data) {
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
        return newnode;
    }

    @Override
    public String toString() {
        return "DLinkedList [head=" + head + ", tail=" + tail + ", size=" + size + "]";
    }

}