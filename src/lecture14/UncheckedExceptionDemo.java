package lecture14;

import java.io.IOException;

/* Unchecked Exception Demo
 * code throws exceptions and the compiler will be fine with that. */
public class UncheckedExceptionDemo {
    public static void main(String[] args) {
        String today = "Thursday";
        /* the code runs without crashing, Process finished with exit code 1 */
        if (today == "Thursday") {
            throw new RuntimeException("this is an Unchecked exception!");
        }

        /* code crashes! unreported exception, must be caught or declared to be thrown */
        if (today == "Thursday") {
            throw new IOException("this is a checked exception!");
        }
    }
}