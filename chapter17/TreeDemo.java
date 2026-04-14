package chapter17;

public class TreeDemo {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("Martha");
        tree.addChild(tree.getRoot(), "Bill");
        TreeNode<String> tomNode = tree.addChild(tree.getRoot(), "Tom");
        tree.addChild(tomNode, "Bob");
        TreeNode<String> lisaNode = tree.addChild(tomNode, "Lisa");
        tree.addChild(lisaNode, "Lisa2");
        tree.addChild(tree.getRoot(), "Jerry");
        System.out.println(tree);
        System.out.println(tree.leaves());
    }
}
