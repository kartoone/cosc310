package chapter17;

import java.util.ArrayList;

public class Tree<E> {

    protected int size = 0; // real-time size
    protected int height = 0; // cached height
    protected boolean changed = false; // for knowing when we need to recalculate the height
    protected Node<E> root = null;

    public Tree(E rootdata) {
        root = new Node<>(null, rootdata);
        size = 1;
        changed = true;
    }

    public void addChild(Node<E> parent, Node<E> child) {
        // 1. Let the parent know they have a new child
        parent.addChild(child);

        // 2. Update the size
        size += child.size(); // adding the child node adds all its children, too!

        // 3. Set the flag to recalculate the height
        changed = true;
    }

    public Node<E> addChild(Node<E> parent, E childData) {
       Node<E> childNode = new Node<>(parent, childData);
       addChild(parent, childNode); 
       changed = true;
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
    public int height() {
        if (!changed) {
            return height;
        }

        // 1. find all the leaves
        ArrayList<Node<E>> leaves = leaves();
        int themax = Integer.MIN_VALUE;
        for (Node<E> node : leaves) {
            int ndepth = node.depth();
            if (ndepth>themax) {
                themax = ndepth;
            }
        }

        // update the cache!
        height = themax;
        changed = false;
        return themax;
    }

    public int size() {
        return size;
    }    

    protected int recalculateSize() {
        if (root==null)
            size = 0;
        else
            size = root.size();
        return size;
    }
    
    public ArrayList<Node<E>> leaves() {
        // use the recursive helper starting at the root
        return leafHelper(root);        
    }

    protected ArrayList<Node<E>> leafHelper(Node<E> n) {
        ArrayList<Node<E>> theleaves = new ArrayList<>();
        if (n.children.isEmpty()) {
            // handle the highly unusual situation where the root of the tree is the tree's only leaf
            theleaves.add(n);
            return theleaves;
        }
        for (Node<E> child : n.children) {
            theleaves.addAll(leafHelper(child));
        }
        return theleaves;
    }

    // return true if n1 is an ancestor of n2
    public boolean ancestor(Node<E> n1, Node<E> n2) {
        if (n2==root) {
            return false; // if n2 is the root, impossible to have an ancestor
        } else if (n1==root) {
            return true;
        } else if (n2.parent==n1) {
            return true;
        } else {
            return ancestor(n1, n2.parent);
        }
    }

    // return true if n1 is a descendant of n2
    // use our ancestor method and swap the nodes
    public boolean descendant(Node<E> n1, Node<E> n2) {
        return ancestor(n2, n1);
    }


}
