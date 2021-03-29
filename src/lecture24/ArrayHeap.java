package lecture24;

public class ArrayHeap<T> implements ExtrinsicPQ<T> {

    private Node[] contents;
    private int size;

    /* constructor of ArrayHeap */
    public ArrayHeap() {
        contents = new ArrayHeap.Node[16];
        /* Add a dummy node at index[0],
        * the heap starts from index[1] so that
        * getLeft, getRight, and getParent methods are nicer. */
        contents[0] = null;
        /* a dummy node at the front of the heap,
        * its size is 0 because nothing has been inserted yet. */
        size = 0;
    }

    /* Returns the left child's index of the given index. */
    private static int getLeft(int i) {
        return i * 2;
    }

    /* Returns the right child's index of the given index. */
    private static int getRight(int i) {
        return i * 2 + 1;
    }

    /* Returns the parent's index of the given index. */
    private static int getParent(int i) {
        return i / 2;
    }

    /* Returns the node at given index. */
    private Node getNode(int i) {
        if (! inBounds(i)) {
            return null;
        }
        return contents[i];
    }

    /* Returns true if the index corresponds to a valid item.
    * if we have 5 items in the PQ, valid indices are 1, 2, 3, 4, 5.
    * 0 is not a valid one because we put a dummy node at index 0.
    * Note that size is 5, and length of the ArrayHeap should be 6 in this case. */
    private boolean inBounds(int i) {
        if ((i > size) || (i < 1)) {
            return false;
        }
        return true;
    }

    /* Swap the nodes at two indices provided. */
    private void swap(int i, int j) {
        Node inode = getNode(i);
        Node jnode = getNode(j);
        contents[i] = jnode;
        contents[j] = inode;
    }

    /* Returns the index of the node with smaller priority value.
    * Precondition: not both nodes are null. */
    private int getMin(int i, int j) {
        Node inode = getNode(i);
        Node jnode = getNode(j);
        if (inode == null) {
            return j;
        } else if (jnode == null) {
            return i;
        } else if (inode.getPriority() < jnode.getPriority()) {
            return i;
        } else {
            return j;
        }
    }

    /* Throws an exception if given index is invalid. */
    private void validateSinkSwimArg(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Cannot sink or swim nodes with index smaller than 1.");
        }
        if (i > size) {
            throw new IllegalArgumentException("Cannot sink or swim nodes with index larger than size.");
        }
        if (getNode(i) == null) {
            throw new IllegalArgumentException("Cannot sink or swim a null node.");
        }
    }

    /* Swims the node (up) currently at given index.
    * Swam the item with its parent until it is larger than its parent.
    * MIN-HEAP property: parent should have a smaller value than its children! */
    private void swim(int i) {
        /* throws an exception if given index is invalid. */
        validateSinkSwimArg(i);

        int parentIndex = getParent(i);
        if (parentIndex == 0) {
            return;
        }
        /* as long as node at given index is smaller than its parent,
        * it should be swam. */
        if (getMin(i, parentIndex) == i) {
            swap(i, parentIndex);
            /* after swap, the node at given index[i] has been swapped to position of its parent node.
            * now the node we need to further look at is at index[itsParent].
            * so we need to further swim its parentIndex. */
            swim(parentIndex);
        }
    }

    /* Sinks the node currently at given index.
    * Swap the item with one of its child until it is smaller than both of its children.
    * Swap with the smaller child if two children are not equal, otherwise swap with arbitrary one. */
    private void sink(int i) {
        validateSinkSwimArg(i);

        int leftChild = getLeft(i);
        int rightChild = getRight(i);
        if (leftChild > size) {
            return;
        }
        if (getMin(i, leftChild) == leftChild) {
            if (rightChild <= size && getMin(leftChild, rightChild) == rightChild) {
                swap(i, rightChild);
                sink(rightChild);
            } else {
                swap(i, leftChild);
                sink(leftChild);
            }
        } else {
            if (rightChild <= size && getMin(i, rightChild) == rightChild) {
                swap(i, rightChild);
                sink(rightChild);
            }
        }
    }

    /* Resizes the ArrayHeap when necessary. */
    private void resize(int capacity) {
        Node[] tmp = new ArrayHeap.Node[capacity];
        for (int i = 1; i < this.contents.length; i += 1) {
            tmp[i] = this.contents[i];
        }
        this.contents = tmp;
    }

    /* Inserts an item with given priority value. This is 'enqueue' or 'offer'.
    * First, add item to the end of the ArrayHeap, which is at index of the leftmost open spot at the bottom level.
    * Then, swim it until it is larger than its parent.*/
    @Override
    public void insert(T item, double priority) {
        /* if the array is full, resize it. */
        if (size + 1 == contents.length) {
            resize(contents.length * 2);
        }

        Node inserted = new Node(item, priority);
        int indexOfInsert = size + 1;
        contents[indexOfInsert] = inserted;
        size += 1;
        swim(indexOfInsert);
    }

    /* Returns the node with smallest priority value, and removes it from the heap.
    * This is 'dequeue', or 'poll'.
    * First, swap the last item into the root position. [size] to [1].
    * Remember to remove the min node after swap,
    * by setting contents[size] to null.
    * Then sink current root until it is smaller than both of its children.
    *  */
    @Override
    public T removeMin() {
        if (isEmpty()) {
            return null;
        }
        T minValue = getNode(1).getItem();
        swap(1, size);
        contents[size] = null;
        size -= 1;
        sink(1);
        return minValue;
    }

    /* Returns the number of items in the heap
    * This is one less than the length of the ArrayHeap because
    * we leave the 0th element empty. */
    @Override
    public int size() {
        return size;
    }

    /* Returns true if the heap is empty. */
    private boolean isEmpty() {
        return size() == 0;
    }


    /* Returns the node with smallest priority value, but does not remove it from the heap. */
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return getNode(1).getItem();
    }

    /* Change the node with given item to have the given priority.
    * Assume the heap has no duplicates. */
    @Override
    public void changePriority(T item, double priority) {
        for (int i = 1; i <= size; i += 1) {
            Node cur = getNode(i);
            if (cur.getItem().equals(item)) {
                double prevPriority = cur.getPriority();
                cur.myPriority = priority;
                if (prevPriority < priority) {
                    sink(i);
                } else if (prevPriority > priority) {
                    swim(i);
                } else {
                    return;
                }
            }
        }
    }

    /* nested class: Node */
    private class Node {
        private T myItem;
        private double myPriority;
        private Node(T item, double priority) {
            myItem = item;
            myPriority = priority;
        }
        public T getItem() {
            return myItem;
        }
        public double getPriority() {
            return myPriority;
        }
        @Override
        public String toString() {
            return myItem.toString() + ", " + myPriority;
        }
    }

}
