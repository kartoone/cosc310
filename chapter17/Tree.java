package chapter17;

import java.util.ArrayList;

public class Tree<E> {

    protected int size = 0;
    protected Node<E> root = null;

    public Tree(E rootdata) {
        root = new Node<>(null, rootdata);
        size = 1;
    }

    public void addChild(Node<E> parent, Node<E> child) {
        // 1. Let the parent know they have a new child
        parent.addChild(child);

        // 2. Update the size
        size += child.size(); // adding the child node adds all its children, too!
    }

    public Node<E> addChild(Node<E> parent, E childData) {
       Node<E> childNode = new Node<>(parent, childData);
       addChild(parent, childNode); 
       return childNode;
    }

    @Override
    public String toString() {
        return String.format("%s [size=%d, height=%d]", root.data.toString(), size(), height());
    }

    public Node<E> getRoot() {
        return root;
    }

    // find the maximum depth leaf!
    private int height() {
        // 1. find all the leaves
        return -1;
    }

    private int size() {
        return size;
    }    
    
    public ArrayList<Node<E>> leaves() {
        ArrayList<Node<E>> theleaves = new ArrayList<>();
        if (size()==1) {
            // handle the highly unusual situation where the root of the tree is the tree's only leaf
            theleaves.add(root);
            return theleaves;
        }

        // recursively find the rest of the leaves in an in-order traversal of the tree

        return theleaves;        
    }
}
