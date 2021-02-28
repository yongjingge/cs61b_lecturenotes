package lecture3;

public class IntList {

    private int first;
    private IntList rest;

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    public int getSize() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.getSize();
    }

    public int iterativeSize() {
        IntList L = this;
        int size = 0;
        while(L != null) {
            size += 1;
            L = L.rest;
        }
        return size;
    }

    public int get(int x) {
        if (x == 0) {
            return first;
        }
        return rest.get(x - 1);
    }

    public int iterativeGet(int x) {
        IntList L = this;
        if (x == 0) {
            return L.first;
        }
        for (int i = 0; i < x; i += 1) {
            L = L.rest;
        }
        return L.first;
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. */
    public static IntList incrList(IntList L, int x) {
        if (L == null) {
            return null;
        }
//        IntList Q = new IntList(L.first, L.rest);
//        IntList res = Q; // copy the address
//        while (Q != null) {
//            Q.first += x;
//            Q = Q.rest;
//        }
//        return res;

        IntList Q = new IntList(L.first + x, null);
        IntList pointer = Q;
        L = L.rest;
        while (L != null) {
            pointer.rest = new IntList(L.first + x, null);
            L = L.rest;
            pointer = pointer.rest;
        }
        return Q;
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed to use
     * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        if (L == null) {
            return null;
        }
        IntList Q = L; // L's value will be changed
        while (L != null) {
            L.first += x;
            L = L.rest;
        }
        return Q;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
//        IntList L = this;
//        int size = L.getSize();
//        for (int i = 0; i < size; i += 1) {
//            res.append(L.get(i));
//            res.append(" ");
//        }
        for (IntList p = this; p != null; p = p.rest) {
            res.append(p.first);
            res.append(" ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);
        L.rest.rest.rest = new IntList(11, null);

        System.out.println("The size of this IntList is: " + L.getSize());
        System.out.println("The size of this IntList is: " + L.iterativeSize());

        System.out.println("The 3rd item in this IntList is: " + L.get(3));
        System.out.println("The 3rd item in this IntList is: " + L.iterativeGet(3));

        System.out.println("Before increment, L is: " + L.toString());
        System.out.println("After increment, L is now: " + incrList(L, 3).toString());

        L.rest.rest.rest.rest = new IntList(17, null);
        System.out.println("Before increment, L is: " + L.toString());
        System.out.println("After increment, L is now: " + dincrList(L, 3).toString());
    }
}
