package lecture5;

/* main method's parameter as an array of objects...
* from CS61B 2006 Lecture5 Iteration and Arrays */
public class Echo {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i += 1) {
            System.out.println(args[i]);
        }
    }
}
