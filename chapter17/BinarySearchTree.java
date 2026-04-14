package chapter17;

public class BinarySearchTree<E> extends BinaryTree<E> {
    
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(E rootData) {
        super(rootData);
    }

    @Override
    public BinaryTreeNode<E> setLeft(BinaryTreeNode<E> parent, BinaryTreeNode<E> newleft) {
        throw new RuntimeException("you cannot do this!!!");
    }

    @Override
    public BinaryTreeNode<E> setLeft(BinaryTreeNode<E> parent, E leftdata) {
        throw new RuntimeException("you cannot do this!!!");
    }

    @Override
    public BinaryTreeNode<E> setRight(BinaryTreeNode<E> parent, BinaryTreeNode<E> newright) {
        throw new RuntimeException("you cannot do this!!!");
    }

    @Override
    public BinaryTreeNode<E> setRight(BinaryTreeNode<E> parent, E right) {
        throw new RuntimeException("you cannot do this!!!");
    }

    
    
}
