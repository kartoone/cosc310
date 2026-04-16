package chapter17;

import static org.junit.Assert.*;
import org.junit.*;

public class BinaryTreeNodeTest {
    
    protected BinaryTreeNode<String> node; // this is the node that is being tested
    protected BinaryTreeNode<String> leftnode; // this is the node that is being tested
    protected BinaryTreeNode<String> rightnode; // this is the node that is being tested


    @BeforeClass
    public static void setupOnce() {

    }

    @Before
    public void setup() {
        leftnode = new BinaryTreeNode<>(null,null,null,"I am left");
        rightnode = new BinaryTreeNode<>(null,null,null,"I am right");
        node = new BinaryTreeNode<>(null,leftnode, rightnode,"Martha");
        leftnode.parent = node;
        rightnode.parent = node;
    }

    @Test
    public void testLeft() {
        assertEquals(leftnode,node.left());
    }

    @Test
    public void testRight() {
        assertEquals(rightnode,node.right());
    }

    @Test
    public void testSetLeft() {

    }

    @Test
    public void testSetRight() {

    }

    @Test
    public void testToString() {
        assertEquals("Martha",node.toString());
    }
}
