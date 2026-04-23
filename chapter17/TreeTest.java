package chapter17;


import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;


public class TreeTest {

    Tree<String> starwars;

    @Before
    public void setup() {
        starwars = new Tree<>("Mary");
        starwars.addChild(starwars.root, "Sam");
        TreeNode<String> hans = starwars.addChild(starwars.root, "Hans");
        starwars.addChild(hans, "Luke");
        starwars.addChild(hans, "Chewey");
        starwars.addChild(starwars.root, "Ben");
    }

    @Test
    public void testPreOrder() {
        ArrayList<TreeNode<String>> result = starwars.preOrder();
        String expected = "[Mary, Sam, Hans, Luke, Chewey, Ben]";
        String actual = result.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testPreOrder2() {
        ArrayList<TreeNode<String>> result = starwars.preOrder(starwars.root.children.get(1));
        String expected = "[Hans, Luke, Chewey]";
        String actual = result.toString();
        assertEquals(expected, actual);
        result = starwars.preOrder(starwars.root.children.get(0));
        expected = "[Sam]";
        actual = result.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testPreOrder3() {
        ArrayList<TreeNode<String>> result = new ArrayList<>();
        starwars.preOrder(starwars.root.children.get(1), result);
        String expected = "[Hans, Luke, Chewey]";
        String actual = result.toString();
        assertEquals(expected, actual);
        result = new ArrayList<>();
        starwars.preOrder(starwars.root.children.get(0), result);
        expected = "[Sam]";
        actual = result.toString();
        assertEquals(expected, actual);

    }

    @Test
    public void testPostOrder() {
        ArrayList<TreeNode<String>> result = starwars.postOrder();
        String expected = "[Sam, Luke, Chewey, Hans, Ben, Mary]";
        String actual = result.toString();
        assertEquals(expected, actual); 
    }

    @Test
    public void testPostOrder2() {
        ArrayList<TreeNode<String>> result = new ArrayList<>();
        starwars.postOrder(starwars.root.children.get(1), result);
        String expected = "[Luke, Chewey, Hans]";
        String actual = result.toString();
        assertEquals(expected, actual);
        result = new ArrayList<>();
        starwars.postOrder(starwars.root.children.get(0), result);
        expected = "[Sam]";
        actual = result.toString();
        assertEquals(expected, actual);

        
    }
}
