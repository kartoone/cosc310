package chapter17;

import java.util.ArrayList;

public class BinaryTree<E> {

    protected int size = 0; // real-time size
    protected int height = -1; // cached height
    protected boolean changed = false; // for knowing when we need to recalculate the height
    protected BinaryTreeNode<E> root = null;

    public BinaryTree() {
        size = 0;
    }

    public BinaryTree(E rootData) {
        root = new BinaryTreeNode<>(null, null, null, rootData);
        size = 1;
        changed = true;
    }

    @Override
    public String toString() {
        return String.format("%s [size=%d, height=%d]", root.data.toString(), size(), height());
    }

    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    // check if the node is in the tree to see if we ever "hit" the root
    // on our upwards traversal from wherever we started
    private boolean checkNode(BinaryTreeNode<E> node) {
        if (node == null)
            return false;
        else if (node == root)
            return true;
        else
            return checkNode(node.parent);
    }

    // dangerous ... make sure newright isn't a node already in the tree
    // unless you have removed it from that part of the tree already!
    // returns the old right node
    /**
     * Replaces the right child of the parent specified.
     * @param parent the parent who is getting a new right child
     * @param newright the new right child
     * @return old right node that is being replaced
     */
    public BinaryTreeNode<E> setRight(BinaryTreeNode<E> parent, BinaryTreeNode<E> newright) {
        BinaryTreeNode<E> oldright = parent.right;
        parent.setRight(newright);
        changed = true;
        size = root.size();
        return oldright;
    }

    public BinaryTreeNode<E> setRight(BinaryTreeNode<E> parent, E right) {
        BinaryTreeNode<E> newright = new BinaryTreeNode<E>(parent, null, null, right);
        parent.setRight(newright);
        changed = true;
        size = root.size();
        return newright;
    }

    // dangerous ... make sure newleft isn't a node already in the tree
    // unless you have removed it from that part of the tree already!
    // returns the old left node
    /**
     * Replaces the left child of the parent specified.
     * @param parent the parent who is getting a new left child
     * @param newleft the new left child
     * @return old left node that is being replaced
     */
    public BinaryTreeNode<E> setLeft(BinaryTreeNode<E> parent, BinaryTreeNode<E> newleft) {
        BinaryTreeNode<E> oldleft = parent.left;
        parent.setLeft(newleft);
        changed = true;
        size = root.size();
        return oldleft;
    }

    public BinaryTreeNode<E> setLeft(BinaryTreeNode<E> parent, E leftdata) {
        BinaryTreeNode<E> newleft = new BinaryTreeNode<E>(parent, null, null, leftdata);
        parent.setLeft(newleft);
        changed = true;
        size = root.size();
        return newleft;
    }

    // find the maximum depth leaf!
    public int height() {
        if (!changed) {
            return height;
        }

        // 1. find all the leaves
        ArrayList<BinaryTreeNode<E>> leaves = leaves();
        int themax = Integer.MIN_VALUE;
        for (BinaryTreeNode<E> node : leaves) {
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
    
    public ArrayList<BinaryTreeNode<E>> leaves() {
        // use the recursive helper starting at the root
        return leafHelper(root);        
    }

    protected ArrayList<BinaryTreeNode<E>> leafHelper(BinaryTreeNode<E> n) {
        ArrayList<BinaryTreeNode<E>> theleaves = new ArrayList<>();
        if (n.left!=null) {
            theleaves.addAll(leafHelper(n.left));
        }
        if (n.right!=null) {
            theleaves.addAll(leafHelper(n.right));
        }
        if (n.left == null && n.right == null) {
            theleaves.add(n);
        }
        return theleaves;
    }

    // return true if n1 is an ancestor of n2
    public boolean ancestor(BinaryTreeNode<E> n1, BinaryTreeNode<E> n2) {
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
    public boolean descendant(BinaryTreeNode<E> n1, BinaryTreeNode<E> n2) {
        return ancestor(n2, n1);
    }

}
