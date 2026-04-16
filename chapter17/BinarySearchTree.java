package chapter17;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
    
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(E rootData) {
        super(rootData);
        addFakeLeaves(root);
    }

    // whenever we add a new node, we need to add fake leaves to receive new data that is not currently in the tree
    protected void addFakeLeaves(BinaryTreeNode<E> node) {
        if (node==null)
            return;
        node.setLeft(new BinaryTreeNode<>(null, null, null, null));
        node.setRight(new BinaryTreeNode<>(null, null, null, null));
    }

    public BinaryTreeNode<E> search(E data) {
        return search(root, data);
    }

    // return null if not found, otherwise return the node that contains the data
    protected BinaryTreeNode<E> search(BinaryTreeNode<E> node, E data) {
        if (node==null)
            return null;
        if (node.data==null) // this is a fake leaf, so we return null to indicate not found
            return null;
        if (data.equals(node.data))
            return node;
        else if (data.compareTo(node.data)<0)
            return search(node.left, data);
        else
            return search(node.right, data);
    }

    protected BinaryTreeNode<E> add(E data) {
        BinaryTreeNode<E> newnode = add(root, data);
        addFakeLeaves(newnode); // we need to add fake leaves to the new node that we just added to the tree
        return newnode;
    }

    protected BinaryTreeNode<E> add(BinaryTreeNode<E> node, E data) {
        if (node==null) {
            root = new BinaryTreeNode<>(null, null, null, data);
            size = 1;
            changed = true;
            return root;
        }
        if (node.data==null) { // this is a fake leaf, so we replace it with a new node that contains the data
            node.data = data;
            addFakeLeaves(node);
            return node;
        }
        if (data.equals(node.data)) {
            return node; // do not add duplicates to the tree (update this to append to list of values if we want duplicates)
        } else if (data.compareTo(node.data)<0) {
            BinaryTreeNode<E> newnode = add(node.left, data);
            return newnode;
        } else {
            BinaryTreeNode<E> newnode = add(node.right, data);
            return newnode;
        }
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

    @Override
    public String toString() {
        String out = "BinarySearchTree with " + size + " nodes and height " + height() + "\n";
        out += displayNode(root, 0);
        return out;
    }

    protected String displayNode(BinaryTreeNode<E> node, int indent) {
        if (node==null || node.data==null) // this is a fake leaf, so we return an empty string to omit displaying it
            return "";
        String out = "";
        out += " ".repeat(indent) + node.data + "\n";
        out += displayNode(node.left, indent + 2);
        out += displayNode(node.right, indent +2);
        return out;
    } 
    
}
