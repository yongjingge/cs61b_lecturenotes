package lab12;

import edu.princeton.cs.algs4.Queue;

/* This class provides static methods for sorting a linked-list based Queue structure.
* It implements the 3-way merge partitioning scheme.
* All functions will operate on the Princeton Queue implementation, which implements
* a queue using a linked list.
* more to see https://github.com/yongjingge/cs61b/blob/master/lab12/ */
public class QuickSort {

    /* 3-way merge partitioning scheme */
    private static <Item extends Comparable> void partition(Queue<Item> unsorted, Item pivot, Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        for (Item cur : unsorted) {
            int cmp = cur.compareTo(pivot);
            if (cmp < 0) {
                less.enqueue(cur);
            } else if (cmp > 0) {
                greater.enqueue(cur);
            } else {
                equal.enqueue(cur);
            }
        }
    }

    /* quick sort main sort method */
    public static <Item extends Comparable> Queue<Item> sort(Queue<Item> unsorted) {
        // corner case
        if (unsorted.size() <= 1) {
            return unsorted;
        }
        // get a randomly selected pivot
        Item pivot = getRandom(unsorted);
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();

        // partition using 3-way scheme
        partition(unsorted, pivot, less, equal, greater);

        // recursion
        Queue<Item> sortedL = sort(less);
        Queue<Item> sortedG = sort(greater);

        // catenate sorted queues
        Queue<Item> res = catenate(sortedL, equal);
        res = catenate(res, sortedG);
        return res;
    }

    /* helper method: catenate sorted queues */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> a, Queue<Item> b) {
        Queue<Item> res = new Queue<>();
        for (Item item : a) {
            res.enqueue(item);
        }
        for (Item item : b) {
            res.enqueue(item);
        }
        return res;
    }

    /* helper method: pick a random pivot */
    private static <Item extends Comparable> Item getRandom(Queue<Item> unsorted) {
        int randomIndex = (int) (Math.random() * unsorted.size());
        Item pivot = null;
        for (Item cur : unsorted) {
            if (randomIndex == 0) {
                pivot = cur;
                break;
            }
            randomIndex -= 1;
        }
        return pivot;
    }

    public static void main(String[] args) {
        Queue<Integer> queueOfNumbers = new Queue<>();
        queueOfNumbers.enqueue(11);
        queueOfNumbers.enqueue(1);
        queueOfNumbers.enqueue(27);
        queueOfNumbers.enqueue(35);
        queueOfNumbers.enqueue(24);
        Queue<Integer> afterQuickSort = sort(queueOfNumbers);
        System.out.println("The original queue of Integer is: " + queueOfNumbers.toString());
        System.out.println("After QuickSort, the queue is: " + afterQuickSort.toString());

        Queue<String> queueOfStrings = new Queue<>();
        queueOfStrings.enqueue("this");
        queueOfStrings.enqueue("is");
        queueOfStrings.enqueue("a");
        queueOfStrings.enqueue("queue");
        queueOfStrings.enqueue("of");
        queueOfStrings.enqueue("strings");
        Queue<String> afterQuickSort2 = sort(queueOfStrings);
        System.out.println("The original queue of String is: " + queueOfStrings.toString());
        System.out.println("After QuickSort, the queue is: " + afterQuickSort2.toString());
    }

}
