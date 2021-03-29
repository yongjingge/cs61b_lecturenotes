package lecture24;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayHeap {

    @Test
    public void testIndexing() {
        assertEquals(6, ArrayHeap.getLeft(3));
        assertEquals(10, ArrayHeap.getLeft(5));
        assertEquals(7, ArrayHeap.getRight(3));
        assertEquals(11, ArrayHeap.getRight(5));

        assertEquals(3, ArrayHeap.getParent(6));
        assertEquals(5, ArrayHeap.getParent(10));
        assertEquals(3, ArrayHeap.getParent(7));
        assertEquals(5, ArrayHeap.getParent(11));
    }

    @Test
    public void testSwimAndSink() {
        ArrayHeap<String> pq = new ArrayHeap<>();

        for (int i = 1; i <= 7; i += 1) {
            pq.insert("x" + i, i);
        }

        System.out.println("PQ x6's priority is 6.");
        System.out.println(pq);

        // Change item x6's priority to a low value.
        System.out.println("Now change x6's priority to 0.");
        pq.getNode(6).setMyPriority(0);
        System.out.println("PQ before swimming:");
        System.out.println(pq);

        // Swim x6 upwards. It should reach the root.
        pq.swim(6);
        System.out.println("PQ after swimming:");
        System.out.println(pq);
        assertEquals("x6", pq.getNode(1).getItem());
        assertEquals("x2", pq.getNode(2).getItem());
        assertEquals("x1", pq.getNode(3).getItem());
        assertEquals("x4", pq.getNode(4).getItem());
        assertEquals("x5", pq.getNode(5).getItem());
        assertEquals("x3", pq.getNode(6).getItem());
        assertEquals("x7", pq.getNode(7).getItem());

        pq.changePriority("x6", 6);
        System.out.println("PQ x6's priority has changed back to 6.");
        System.out.println(pq);

        // now test Sink
        // Change root's priority value to a large value.
        System.out.println("Now change x1's priority to 10.");
        pq.getNode(1).setMyPriority(10);
        System.out.println("PQ before sinking:");
        System.out.println(pq);

        // Sink x1 downwards. It should reach the bottom.
        pq.sink(1);
        System.out.println("PQ after sinking:");
        System.out.println(pq);
    }

}
