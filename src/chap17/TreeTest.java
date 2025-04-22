package chap17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {

    protected Tree<String> airports;

    @Before
    public void setup() {
        airports = new Tree<>("BHM");
        Assert.assertEquals(1, airports.size());
        Assert.assertEquals("height was wrong in setup", 1, airports.height());
    }

    @Test
    public void testAddChild() {
        Object returnval = airports.addChild(airports.root(), "ATL");
        Assert.assertEquals("ATL: 0 children, 2 DEEP", returnval.toString());
        Assert.assertEquals(2, airports.size());
        Assert.assertEquals(2, airports.height());
    }

    @Test
    public void testHeight() {

    }

    @Test
    public void testHeight2() {

    }

    @Test
    public void testRoot() {

    }

    @Test
    public void testSearch() {

    }

    @Test
    public void testSearch2() {

    }

    @Test
    public void testSearchBFS() {

    }

    @Test
    public void testSearchBFS2() {

    }

    @Test
    public void testSize() {

    }

    @Test
    public void testSize2() {

    }

    @Test
    public void testToString() {

    }

    @Test
    public void testToString2() {

    }
}
