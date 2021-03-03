package lecture9;

import lecture5.SLList04;

/* Extends a class */
public class VengefulSLList<Stuff> extends SLList04<Stuff> {

    SLList04<Stuff> deletedItems;

    /* sub-class's constructor
    * - are not inherited from its super-class.
    * - one should explicitly call SLList's constructor using the keyword 'super',
    * - if not, Java will automatically do it. */
    public VengefulSLList() {
        super();
        deletedItems = new SLList04<>();
    }

    /* using extend will inherit all members of the super-class,
    * except the private members and the constructor.
    * if we want to use the private method from its super-class,
    * we use the super keyword. */
    @Override
    protected Stuff removeLast() {
        Stuff removed = super.removeLast();
        /* notice the difference between below operations */
        deletedItems.addFirst(removed); // namely 'this.deletedItems.addFirst()'
        addFirst(removed); // namely 'this.addFirst()', will add back the deleted item
        return removed;
    }

    public static void main(String[] args) {
        /* notice: SLList04's constructor will be invoked twice. one time for super(), the other time for deletedItems. */
        VengefulSLList<Integer> L = new VengefulSLList<>();
        L.addFirst(1);
        System.out.println("There should have nothing in current deletedItems.");
        L.deletedItems.printSLList();
        System.out.println("There should have 1 element in current L.");
        L.printSLList();
        System.out.println("L's size is " + L.size());

        L.addFirst(11);
        L.addLast(13);
        L.addLast(15);
        System.out.println("L is now ");
        L.printSLList();
        L.removeLast();
        System.out.println("After removing the last item, L is now ");
        L.printSLList();
        System.out.println("L's deletedItems field is now ");
        L.deletedItems.printSLList();

        System.out.println(L.size());
    }
}
