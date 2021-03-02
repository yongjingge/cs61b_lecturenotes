package lecture7;

/* test the Sort class */
import org.junit.Test;
import static org.junit.Assert.*;
public class TestSort {

    @Test
    public void testFindSmallestIndex() {
        String[] s = {"i", "have", "an", "egg"};
        int smallestIndex = Sort.findSmallest(s, 0);
        assertEquals(2, smallestIndex);
    }

    @Test
    public void testSwap() {
        String[] s = {"i", "have", "an", "egg"};
        String[] afterSwap = {"an", "have", "i", "egg"};
        Sort.swap(s, 0, 2);
        assertArrayEquals(afterSwap, s);
    }

    @Test
    public void testSort() {
        String[] s = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(s);
        assertArrayEquals(expected, s);
    }
}
