package chapter17;

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
        

    }

    @Test
    public void testPreOrder2() {

    }

    @Test
    public void testPreOrder3() {

    }
}
