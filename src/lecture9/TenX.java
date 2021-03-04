package lecture9;

/* demonstrating the Higher Order Function */
public class TenX implements IntUnaryFunction {
    @Override
    public int apply(int x) {
        return 10 * x;
    }
}
