package lecture9;

/* demonstrating the Higher Order Function
* which is a function that treats another function as input. */
public class HOFdemo {

    /* we set do_twice as a static method because we don't need to instantiate a HOFdemo to run this method */
    public static int do_twice(IntUnaryFunction funciton, int x) {
        return funciton.apply(funciton.apply(x));
    }

    public static void main(String[] args) {
        IntUnaryFunction tenX = new TenX();
        /* should run the function apply of TenX twice based on int 2 */
        int res = do_twice(tenX, 2);
        System.out.println(res);
    }
}
