package lecture4;

/* SLList01 showing the use of a Nested Class */
public class SLList01 {

    /* IntNode
    * the nested class with a 'private static' configuration */
    private static class IntNode {
        int item;
        IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }

        public String toString() {
            return item + " " + next;
        }
    }

    /* a SLList01 variable */
    private IntNode first;

    /* constructor */
    public SLList01(int x) {
        first = new IntNode(x, null);
    }

    /* get the front item of the list */
    public int getFirst() {
        return first.item;
    }

    /* add x to the front of the list */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    /* add x to the end of the list */
    public void addLast(int x) {
        IntNode p = first;
        while(p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /* return the size of the list,
    * will need a helper method to get the size of an IntNode */
    public int getSize() {
        return size(first);
    }

    /* helper method to get the size of an IntNode */
    private static int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }
        return size(p.next) + 1;
    }

    /* toString method */
    public String toString() {
        return first + " ";
    }

    public static void main(String[] args) {
        /* create a list of one integer, namely 20 */
        SLList01 list = new SLList01(20);
        System.out.println(list.toString());

        /* create a list of one integer 22, and add 33 to the front the list */
        SLList01 list2 = new SLList01(22);
        System.out.println(list2.toString());
        list2.addFirst(33);
        list2.addFirst(44);
        System.out.println(list2.toString());

        /* return the first item of the list */
        System.out.println(list2.getFirst());

        /* add 11 to the end of the list */
        list2.addLast(11);
        System.out.println(list2.toString());
        System.out.println(list2.getSize());

        list2.addLast(10);
        System.out.println(list2.getSize());
        System.out.println(list2.toString());
    }
}
