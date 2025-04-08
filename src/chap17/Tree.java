package chap17;

import java.util.ArrayList;
public class Tree<E> {
    
    protected TreeNode<E> root;

    protected class TreeNode<E> {
        // attributes
        protected TreeNode<E> parent;
        protected ArrayList<TreeNode<E>> children;  
        protected E data;
        public TreeNode(TreeNode<E> parent, ArrayList<TreeNode<E>> children, E data) {
            this.parent = parent;
            this.children = children;
            this.data = data;
        }
        public void addChild(TreeNode<E> newchild) {
            if (this.children == null) {
                this.children = new ArrayList<>();
            }
            this.children.add(newchild);
        }
        @Override
        public String toString() {
            String nodestrrep = data.toString();
            nodestrrep += ": " + (children!=null?children.size():0) + " children, ";
            nodestrrep += parent==null ? "ROOT" : depth() + " DEEP";
            return nodestrrep;
        }

        protected int depth() {
            if (parent==null) {
                return 1;
            } else {
                return 1+parent.depth();
            }
        }
    }

    public Tree() {
        // nothing to do here ... we don't have a root yet
    }

    public Tree(E rootdata) {
        root = new TreeNode<>(null, null, rootdata);
    }

    public TreeNode<E> addChild(TreeNode<E> parent, E childdata) {
        // Step 1 - create the child (TreeNode)
        TreeNode<E> child = new TreeNode<>(parent, null, childdata);
        
        // Step 2 - tell the parent it has a new child
        parent.addChild(child);
        
        return child;
    }

    public TreeNode<E> root() {
        return root;
    }

    // the main size() method for the entire tree
    public long size() {       
        return root==null?0:size(root);
    }

    // the size of the subtree rooted at n
    public long size(TreeNode<E> n) {
        long nsize = 1;
        if (n.children != null) {
            for (TreeNode<E> child : n.children) {
                nsize += size(child);
            }
        }
        return nsize;
    }

    public long height() {       
        // finish this!
        return 0;
    }

    // the size of the subtree rooted at n
    public long height(TreeNode<E> n) {
        // finish this!
        return 0;
    }

    /**
     * 
     * @param e - the data we are searching for
     * @return first node containing the data if found otherwise null
     */
    public TreeNode<E> search(E e) {
        return root==null ? null : search(e, root);
    }

    // DFS helper, but also useful for API ... starting your search at a particular node
    public TreeNode<E> search(E e, TreeNode<E> n) {
        if (n.data.equals(e)) {
            return n;
        }
        if (n.children != null) {
            for (TreeNode<E> child : n.children) {
                TreeNode<E> hit = search(e, child);
                if (hit != null) {
                    return hit;
                }
            }            
        }
        return null;
    }

    // alternative search method that uses BFS instead of DFS
    public TreeNode<E> searchBFS(E e) {
        ArrayList<TreeNode<E>> bfsqueue = new ArrayList<>();
        bfsqueue.add(root);
        return searchBFS(e, bfsqueue);
    }

    // BFS helper, but NOT useful for API b/c how do you know what "queue" to pass in?
    protected TreeNode<E> searchBFS(E e, ArrayList<TreeNode<E>> bfsqueue) {        
        if (bfsqueue.isEmpty()) {
            return null;
        }
        TreeNode<E> n = bfsqueue.remove(0);
        if (n.data.equals(e)) {
            return n;
        }
        if (n.children != null) {
            for (TreeNode<E> child : n.children) {
                bfsqueue.add(child);
            }
        }
        return searchBFS(e, bfsqueue);
    }

    @Override
    public String toString() {
        return size()==0?"EMPTY":toString(root, 0);
    }

    public String toString(TreeNode<E> n, int indent) {
        String spacestring = " ".repeat(indent);
        String result = spacestring + n.data.toString();

        // ADD YOUR RECURSIVE CALL HERE ... HINT: LOOP THROUGH THE CHILDREN AND RECURSE ON EACH ONE! ANOTHER HINT: CHECK TO MAKE SURE CHILDREN IS NOT NULL FIRST

        return result;
    }



}
