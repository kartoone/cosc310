package chapter17;

import java.util.ArrayList;

public class TreeNode<E> {

    protected E data;
    protected TreeNode<E> parent;
    protected ArrayList<TreeNode<E>> children = new ArrayList<>();

    public TreeNode(TreeNode<E> parent, E data) {
        this.parent = parent;
        this.data = data;
    }

    public TreeNode(TreeNode<E> parent, ArrayList<TreeNode<E>> children, E data) {
        this.parent = parent;
        this.children = children;
        this.data = data;
    }

    public void addChild(TreeNode<E> child) {
        this.children.add(child);
        child.parent = this;
    }

    // convenience method to calculate how many nodes
    // are in the subtree rooted at this node
    // (counts this node as well)
    public int size() {
        int count = 1;
        for (TreeNode<E> child : children) {
           count += child.size();
        }
        return count;
    }

    public int depth() {
        if (parent==null)
            return 0;
        else
            return 1 + parent.depth();
    }
    @Override
    public String toString() {
        return data.toString();
    }
}
