package lecture8;

public interface List61B<T> {

    /* There will be no private keyword inside an interface. */
    public void addFirst(T x);
    public void addLast(T x);
    public T getFirst();
    public T getLast();
    public T get(int i);
    public T removeFirst();
    public T removeLast();
    public void insert(T x, int position);
    public int size();
    public void resize(int capacity);

    /* use 'default' keyword to specify a method
    * that subclasses should inherit from an interface. */
    default public void print() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
