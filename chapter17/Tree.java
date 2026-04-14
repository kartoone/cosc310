package chapter17;

import java.util.ArrayList;

public class Tree<E> {

    protected int size = 0; // real-time size
    protected int height = 0; // cached height
    protected boolean changed = false; // for knowing when we need to recalculate the height
    protected TreeNode<E> root = null;

    public Tree() {
        size = 0;
    }

    public Tree(E rootdata) {
        root = new TreeNode<>(null, rootdata);
        size = 1;
        changed = true;
    }

    // promote the subtree rooted at node to be the root of the whole tree
    // dangerous b/c if you weren't in the node subtree, you are gone!
    public void setRoot(TreeNode<E> node) {
        root = node;
        size = node.size();
        changed = true;
    }

    // return the old root in case programmer wants to
    // do something with the old root
    public TreeNode<E> setRoot(E rootData) {
        TreeNode<E> oldRoot = root;
        root = new TreeNode<>(null, rootData);
        size = 1;
        return oldRoot;
    }

    public void addChild(TreeNode<E> parent, TreeNode<E> child) {
        // 1. Let the parent know they have a new child
        if (parent == null) 
            setRoot(child);
        else
            parent.addChild(child);

        // 2. Update the size
        size += child.size(); // adding the child node adds all its children, too!

        // 3. Set the flag to recalculate the height
        changed = true;
    }

    public TreeNode<E> addChild(TreeNode<E> parent, E childData) {
       TreeNode<E> childNode = new TreeNode<>(parent, childData);
       addChild(parent, childNode); 
       changed = true;
       return childNode;
    }

    @Override
    public String toString() {
        return String.format("%s [size=%d, height=%d]", root.data.toString(), size(), height());
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    // find the maximum depth leaf!
    public int height() {
        if (!changed) {
            return height;
        }

        // 1. find all the leaves
        ArrayList<TreeNode<E>> leaves = leaves();
        int themax = Integer.MIN_VALUE;
        for (TreeNode<E> node : leaves) {
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
    
    public ArrayList<TreeNode<E>> leaves() {
        // use the recursive helper starting at the root
        return leafHelper(root);        
    }

    protected ArrayList<TreeNode<E>> leafHelper(TreeNode<E> n) {
        ArrayList<TreeNode<E>> theleaves = new ArrayList<>();
        if (n.children.isEmpty()) {
            // handle the highly unusual situation where the root of the tree is the tree's only leaf
            theleaves.add(n);
            return theleaves;
        }
        for (TreeNode<E> child : n.children) {
            theleaves.addAll(leafHelper(child));
        }
        return theleaves;
    }

    // return true if n1 is an ancestor of n2
    public boolean ancestor(TreeNode<E> n1, TreeNode<E> n2) {
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
    public boolean descendant(TreeNode<E> n1, TreeNode<E> n2) {
        return ancestor(n2, n1);
    }


}
