package chapter17;

public class BinaryTreeNode<E> {

    protected E data;
    protected BinaryTreeNode<E> parent;
    protected BinaryTreeNode<E> left;
    protected BinaryTreeNode<E> right;

    public BinaryTreeNode(BinaryTreeNode<E> parent, BinaryTreeNode<E> left, BinaryTreeNode<E> right, E data) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    // it's on you if you extend this!
    protected void setLeft(BinaryTreeNode<E> newleft) {
        newleft.parent = this;
        this.left = newleft;
    }

    protected void setRight(BinaryTreeNode<E> newright) {
        newright.parent = this;
        this.right = newright;
    }

    public BinaryTreeNode<E> left() {
        return left;
    }

    public BinaryTreeNode<E> right() {
        return right;
    }

    // convenience method to calculate how many nodes
    // are in the subtree rooted at this node
    // (counts this node as well)
    public int size() {
        int count = 1;
        if (left != null) {
            count += left.size();
        }
        if (right != null) {
            count += right.size();
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

