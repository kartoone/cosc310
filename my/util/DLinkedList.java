package my.util;

public class DLinkedList<T> {

    private DNode<T> head;
    private DNode<T> tail;
    private int size;

    public DLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the node at position i and returns null if i is invalid.
     *
     * @param i
     * @return null if i is invalid
     */
    public DNode<T> get(int i) {
        if (i >= size || i < 0) {
            return null;
        }

        // decide whether we should start at head or tail
        if (i < size / 2) {
            // start at the head
            DNode<T> n = head;
            for (int j = 0; j < i; j++) {
                n = n.getNext();
            }
            return n;

        } else {
            // start at the tail
            DNode<T> n = tail;
            for (int j = 0; j < size - i - 1; j++) {
                n = n.getPrev();
            }
            return n;
        }
    }

    // need to break the connection between node and whatever its current "next" is
    public void addAfter(DNode<T> node, T data) {
        if (node == tail) {
            add(data); // already adds at the tail
        } else if (node == null) {
            addFirst(data); // already adds at the head
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
        if (size == 0) {
            tail = newnode;
        } else {
            head.setPrev(newnode);
            newnode.setNext(head);
        }
        size++;
        head = newnode;
        return newnode;
    }

    public DNode<T> add(T data) {
        DNode<T> newnode = new DNode<>(tail, null, data);
        if (size == 0) {
            head = newnode;
        } else {
            tail.setNext(newnode);
            newnode.setPrev(tail);
        }
        size++;
        tail = newnode;
        return newnode;
    }

    /**
     * Returns the last node ... null if list is empty
     *
     * @return null if list is empty
     */
    public DNode<T> removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            DNode<T> n = tail;
            head = null;
            tail = null;
            size--;
            return n;
        }

        // only make it to here in a normal sized list
        DNode<T> oldtail = tail;
        tail.getPrev().setNext(null);
        tail = tail.getPrev();
        size--;

        // if and only if the new size is one
        if (size == 1) {
            head = tail;
        }

        // fully detach to avoid accidental misuse
        oldtail.setPrev(null);
        oldtail.setNext(null);

        return oldtail;
    }

    /**
     * Returns the first node ... null if list is empty
     *
     * @return null if list is empty
     */
    public DNode<T> removeFirst() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            DNode<T> n = head;
            head = null;
            tail = null;
            size--;
            return n;
        }

        DNode<T> oldhead = head;
        head = head.getNext();
        head.setPrev(null);
        size--;

        // if and only if the new size is one
        if (size == 1) {
            tail = head;
        }

        // fully detach to avoid accidental misuse
        oldhead.setPrev(null);
        oldhead.setNext(null);

        return oldhead;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "DLinkedList [head=" + head + ", tail=" + tail + ", size=" + size + "]";
    }
}
