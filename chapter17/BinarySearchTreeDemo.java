package chapter17;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<String> nametree = new BinarySearchTree<>("Martha");
        nametree.add("Bill");
        nametree.add("Sally");
        System.out.println(nametree);
    }
}
