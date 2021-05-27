package lab12;

import edu.princeton.cs.algs4.Queue;

/* This class provides static methods for sorting a linked-list based Queue structure.
* All functions will operate on the Princeton Queue implementation, which implements a queue
* using a linked list.
* */
public class MergeSort {

    /* main sort method */
    public static <Item extends Comparable> Queue<Item> sort(Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        }
        /* make single item queues using helper method */
        Queue<Queue<Item>> queueOfQueues = makeSingleItemQueues(items);
        Queue<Item> sub1 = new Queue<>();
        Queue<Item> sub2 = new Queue<>();

        /* DIVIDE the queue of queues */
        int sub1Range = queueOfQueues.size() / 2;
        for (int i = 0; i < sub1Range; i += 1) {
            Item sub1Item = queueOfQueues.dequeue().dequeue();
            sub1.enqueue(sub1Item);
        }

        int sub2Range = queueOfQueues.size();
        for (int i = 0; i < sub2Range; i += 1) {
            Item sub2Item = queueOfQueues.dequeue().dequeue();
            sub2.enqueue(sub2Item);
        }

        /* CONQUER recursively call sort method to deal with the divided sub-queues */
        Queue<Item> sortedSub1 = sort(sub1);
        Queue<Item> sortedSub2 = sort(sub2);

        /* merge two sorted queues */
        return merge(sortedSub1, sortedSub2);
    }

    /* helper method: make single item queues */
    private static <Item extends Comparable> Queue<Queue<Item>> makeSingleItemQueues(Queue<Item> items) {
        if (items.isEmpty() || items == null) {
            return null;
        }
        Queue<Queue<Item>> res = new Queue<>();
        for (Item singleItem : items) {
            Queue<Item> singleQueue = new Queue<>();
            singleQueue.enqueue(singleItem); // create single queue for single item
            res.enqueue(singleQueue); // enqueue the single queue just created to the final queue
        }
        return res;
    }

    /* helper method: merge two sorted queues */
    private static <Item extends Comparable> Queue<Item> merge(Queue<Item> sorted1, Queue<Item> sorted2) {
        Queue<Item> res = new Queue<>();
        while (! (sorted1.isEmpty() && sorted2.isEmpty())) {
            res.enqueue(getMin(sorted1, sorted2));
        }
        return res;
    }

    /* helper method: get min from two queues */
    private static <Item extends Comparable> Item getMin(Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            Comparable q1min = q1.peek();
            Comparable q2min = q2.peek();
            if (q1min.compareTo(q2min) <= 0) {
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /* TDD */
    public static void main(String[] args) {
        Queue<Integer> queueOfNumbers = new Queue<>();
        queueOfNumbers.enqueue(11);
        queueOfNumbers.enqueue(1);
        queueOfNumbers.enqueue(27);
        queueOfNumbers.enqueue(35);
        queueOfNumbers.enqueue(24);
        Queue<Integer> afterMergeSort = sort(queueOfNumbers);
        System.out.println("The original queue of Integer is: " + queueOfNumbers.toString());
        System.out.println("After MergeSort, the queue is: " + afterMergeSort.toString());

        Queue<String> queueOfStrings = new Queue<>();
        queueOfStrings.enqueue("this");
        queueOfStrings.enqueue("is");
        queueOfStrings.enqueue("a");
        queueOfStrings.enqueue("queue");
        queueOfStrings.enqueue("of");
        queueOfStrings.enqueue("strings");
        Queue<String> afterMergeSort2 = sort(queueOfStrings);
        System.out.println("The original queue of String is: " + queueOfStrings.toString());
        System.out.println("After MergeSort, the queue is: " + afterMergeSort2.toString());
    }
}
