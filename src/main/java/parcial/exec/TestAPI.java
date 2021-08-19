package parcial.exec;

import java.util.Scanner;

public class TestAPI {

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\\s|\\n");

        new Menu().execute(scanner);
    }
}
