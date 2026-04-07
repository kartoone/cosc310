package chapter17;

public class TreeDemo {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("Martha");
        tree.addChild(tree.getRoot(), "Bill");
        Node<String> tomNode = tree.addChild(tree.getRoot(), "Tom");
        tree.addChild(tomNode, "Bob");
        tree.addChild(tomNode, "Lisa");
        tree.addChild(tree.getRoot(), "Jerry");
        System.out.println(tree);
    }
}
