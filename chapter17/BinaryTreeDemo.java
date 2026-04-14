package chapter17;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree<String> bintree = new BinaryTree<>("Martha");
        bintree.setLeft(bintree.getRoot(), "Bill");
    }
}
