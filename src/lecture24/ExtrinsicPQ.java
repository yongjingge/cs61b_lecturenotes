package lecture24;

/* lab10: Heap MinPQ interface */
public interface ExtrinsicPQ<T> {
    /* Inserts an item with given priority value. This is also known as 'enqueue' or 'offer'. */
    public void insert(T item, double priority);

    /* Returns the item with minimum priority value. It has the minimum priority value,
    * but it also has the highest priority in the min heap. */
    public T peek();

    /* Returns and removes the minimum item. */
    public T removeMin();

    /* Changes the priority value of the given item. */
    public void changePriority(T item, double priority);

    /* Returns the number of items in the PQ. */
    public int size();
}