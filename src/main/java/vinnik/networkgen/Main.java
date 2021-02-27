package vinnik.networkgen;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please, enter correct number of arguments.");
        } else {
            System.out.println(args[0]);
        }
    }
}
