package chapter17;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<String> nametree = new BinarySearchTree<>("Martha");
        nametree.add("Bill");
        nametree.add("Sally");
        nametree.add("Adam");
        nametree.add("Jerry");
        System.out.println(nametree);
        BinaryTreeNode<String> n = nametree.search("Bill");
        nametree.changeData(n, "Mandy");

        System.out.println(nametree);
    }
}
