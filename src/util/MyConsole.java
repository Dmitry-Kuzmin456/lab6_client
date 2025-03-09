package util;

import java.util.Scanner;

public class MyConsole {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean block = false;

    public static void print(String message) {
        if (!block) {System.out.print(message);}
    }

    public static void println(String message) {
        if (!block) {System.out.println(message);}
    }

    public static String read(){
        return scanner.nextLine();
    }

    public static void setBlock(boolean flag) {
        block = flag;
    }

    public static void setScanner(Scanner scanner) {
        MyConsole.scanner = scanner;
    }

    public static Scanner getScanner() {
        return scanner;
    }


}
