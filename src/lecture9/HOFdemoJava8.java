package lecture9;

import java.util.function.Function;

/* Java8 version of HOFdemo */
public class HOFdemoJava8 {

    public static int tenX(int x) {
        return 10 * x;
    }

    public static int do_twice(Function<Integer, Integer> function, int x) {
        return function.apply(function.apply(x));
    }

    public static void main(String[] args) {
        int res = do_twice(HOFdemoJava8::tenX, 2);
        System.out.println(res);
    }
}
