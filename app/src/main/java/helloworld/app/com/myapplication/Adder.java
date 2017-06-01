package helloworld.app.com.myapplication;

/**
 * Adds numbers together.
 *
 * @author Greyson Parrelli (keybase.io/greyson)
 */
public class Adder {

    private static final Adder INSTANCE = new Adder();

    public static Adder getInstance() {
        return INSTANCE;
    }

    public int add(int n1, int n2) {
        return n1 + n2;
    }
}
