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
        node.setLeft(new BinaryTreeNode<>(node, null, null, null));
        node.setRight(new BinaryTreeNode<>(node, null, null, null));
    }

    public BinaryTreeNode<E> search(E data) {
        return search(root, data);
    }

    // return null if not found, otherwise return the node that contains the data
    protected BinaryTreeNode<E> search(BinaryTreeNode<E> node, E data) {
        if (node==null) // This is only ever true (i.e., node will be null) for an empty tree
            return null;
        if (node.data==null) // this is a fake leaf, so we return null to indicate not found
            return null;
        if (data.equals(node.data))
            return node;
        else if (data.compareTo(node.data)<0) // data we are looking for is LESS THAN the node we are on so look down the left branch
            return search(node.left, data);
        else
            return search(node.right, data);
    }

    protected BinaryTreeNode<E> add(E data) {
        BinaryTreeNode<E> newnode = add(root, data);
        return newnode;
    }

    protected BinaryTreeNode<E> add(BinaryTreeNode<E> node, E data) {
        if (node==null) {
            // the only time we are ever here is if we have a completely empty tree
            // in other words, the root is null
            root = new BinaryTreeNode<>(null, null, null, data);
            size = 1;
            changed = true;
            addFakeLeaves(root);
            return root;
        }
        if (node.data==null) { // this is a fake leaf, so we replace it with a new node that contains the data
            node.data = data;
            size += 1;
            changed = true;
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
        String out = " ".repeat(indent) + node.data + "\n";
        out += displayNode(node.left, indent + 2);
        out += displayNode(node.right, indent +2);
        return out;
    } 

    @Override
    public int height() {
        return super.height()-1;
    }
    
    /**
     * Safely replace the data at node n with newdata 
     * @param n
     * @param newdata
     * @return true if it was possible, false otherwise
     */
    public boolean changeData(BinaryTreeNode<E> n, E newdata) {
        if (n==null)
            return false;

        if (n.parent != null) {
            // must be the root node we are trying to change
            if (n==n.parent.left()) {
                // n must be the left child so the new data has to be less than the parent's data
                if (newdata.compareTo(n.parent.data)>0)
                    return false;
            } else if (n==n.parent.right()) {
                // n must be the right child so the new data has to be greater than the parent's data
                if (newdata.compareTo(n.parent.data)<=0)
                    return false;
            } else {
                // uh-oh this node think it's parent is n.parent,
                // but n.parent doesn't know anything about this node
            }
        }

        // now check the children
        // we can never be in a situation where we don't have children
        if (n.left().data!=null) {
            if (n.left().data.compareTo(newdata)>=0) {
                return false;
            }
        }
        if (n.right().data!=null) {
            if (n.right().data.compareTo(newdata)<0) {
                return false;
            }
        }

        // YES! we made it, we could safely replace the data
        // without destroying the structure of the binary tree
        n.data = newdata;
        return true;

    }
}
