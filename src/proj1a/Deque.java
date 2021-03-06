package proj1a;

public interface Deque<T> {
    /* no need to declare method with public in an interface */
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
