package lecture14;

import java.io.IOException;

/* Checked Exception Demo
* The exception MUST be caught/specified, otherwise the code won't be compiled. */
public class CheckedExceptionDemo {

    public static String today = "Thursday";

    /* SOLUTION 1: Using a Catch statement to handle Checked Exception. */
    public static void gulgate() {
        try {
            if (today == "Thursday") {
                throw new IOException("This is a checked exception!");
            }
        } catch (Exception e) {
            System.out.println("Exception caught! " + e.getLocalizedMessage() + " This demonstrates a successful Catch Statement solution for the Checked Exception!");
        }
    }

    /* SOLUTION 2: Specifying the method using "throws" keyword.
    * can ONLY deal with the exception within method.
    * the exception is remain to be handled outside the method. */
    public static void gulgateSP() throws IOException {
        if (today == "Thursday") {
            throw new IOException("this is a checked exception!");
        }
    }

    public static void main(String[] args) throws IOException {

//        gulgate();

        /* we can use a Catch statement to catch the exception from 'gulgateSP'.
        * That method simply specified itself with a 'throws' keyword,
        * so we need to catch its exception if we want to call that method. */
//        try {
//            gulgateSP();
//        } catch (Exception e) {
//            System.out.println("Caught exception of the gulgateSP method!");
//        }

        /* OR we can just keep using the 'throws' keyword in this main method,
        * and let other methods deal with the remaining exception! */
        gulgateSP();

    }
}
