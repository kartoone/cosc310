package chapter17;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class BinarySearchTreeTest {

    protected BinarySearchTree<String> emptytree;
    protected BinarySearchTree<String> tree;

    @Before
    public void setup() {
        emptytree = new BinarySearchTree<>();
        tree = new BinarySearchTree<>("Ben");
        tree.add("Chewey");
        tree.add("Hans");
        tree.add("Sam");
        tree.add("Luke");
        tree.add("Mary");
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
    public void testInOrder() {
        ArrayList<BinaryTreeNode<String>> result = tree.inOrder();
        String expected = "[Ben, Chewey, Hans, Luke, Mary, Sam]";
        String actual = result.toString();
        assertEquals(expected, actual);
        result = emptytree.inOrder();
        expected = "[]";
        actual = result.toString();
        assertEquals(expected, actual);
    }
}
