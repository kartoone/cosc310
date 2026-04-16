package chapter17;

import static org.junit.Assert.*;
import org.junit.*;

public class BinarySearchTreeTest {

    protected BinarySearchTree<String> emptytree;
    protected BinarySearchTree<String> tree;

    @Before
    public void setup() {
        emptytree = new BinarySearchTree<>();
        tree = new BinarySearchTree<>("Martha");
        tree.add("Bill");
        tree.add("Sally");
        tree.add("Adam");
        tree.add("Jerry");
    }

    @Test
    public void testChangeData() {

    }

    @Test
    public void testHeight() {
        int expectedHeight = -2;
        int actualHeight = emptytree.height();
        assertEquals(expectedHeight, actualHeight);
        emptytree.add("Sam");
        assertEquals(0, emptytree.height());
        expectedHeight = 2;
        actualHeight = tree.height();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    public void testSearch() {

    }

    @Test
    public void testToString() {

    }
}
