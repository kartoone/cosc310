package chap14;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SearcherTest {
    @Test
    public void testBfindFirst() {
        int airports[] = new int[] {0, 1, 2, 9, 25};
        int expected = 0;
        int actual = Searcher.bfindFirst(0, airports);
        assertEquals(expected, actual);
        expected = 1;
        actual = Searcher.bfindFirst(1, airports);
        assertEquals(expected, actual);
        expected = 3;
        actual = Searcher.bfindFirst(9, airports);
        assertEquals(expected, actual);
     }

    @Test
    public void testBfindFirst2() {

    }

    @Test
    public void testFindAll() {

    }

    @Test
    public void testFindFirst() {

    }
}
