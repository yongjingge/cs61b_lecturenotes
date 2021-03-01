package lecture6;

/* AList in Generic type */
public class AListGeneric<Item> {
    private Item[] items;
    private int size;

    /* create an empty AList */
    public AListGeneric() {
        /* need (Item[]) casting, will cause a compiler warning, which you should ignore. */
        items = (Item[]) new Object[10];
        size = 0;
    }

    /* resize */
    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /* add to the back of the list */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

    /* add to the front of the list */
    public void addFirst(Item x) {
        Item[] a = (Item[]) new Object[items.length + 1];
        a[0] = x;
        System.arraycopy(items, 0, a, 1, this.getSize());
        items = a;
        size += 1;
    }

    /* return the back of the list */
    public Item getLast() {
        return items[size - 1];
    }

    /* return the i-th item of the list */
    public Item get(int x) {
        return items[x];
    }

    /* return the number of items in the list */
    public int getSize() {
        return size;
    }

    /* remove and return the last item in the list,
    * need to null out the deleted item! */
    public Item removeLast() {
        Item res = getLast();
        /* make sure no memory is wasted */
        items[size - 1] = null;
        size -= 1;
        return res;
    }

    public void print() {
        for (int i = 0; i < getSize(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        AListGeneric<String> al = new AListGeneric<>();
        al.addFirst("head");
        al.print();
        al.addFirst("beforehead");
        al.print();
        al.addLast("neck");
        al.addLast("back");
        al.print();
    }
}