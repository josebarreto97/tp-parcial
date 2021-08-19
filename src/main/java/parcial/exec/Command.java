package parcial.exec;

import java.util.Scanner;

public abstract class Command {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public abstract void execute(Scanner scanner);

    void error(Scanner scanner, String expected, String actual) {
        System.out.println(ANSI_RED + "Error sintáctico: se esperaba " + ANSI_RESET  + expected + ANSI_RED + " pero se envio " + ANSI_RESET + actual);
        scanner.nextLine();
    }
}
