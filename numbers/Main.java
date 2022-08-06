package numbers;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    final private static BigInteger buzzNumber = new BigInteger("7");

    public static void main(String[] args) {
        input();
    }

    private static void input() {
        String input;
        BigInteger number;

        System.out.printf("Welcome to Amazing Numbers!%n%n" +
                "Supported requests:%n%n" +
                "- enter a natural number to know its properties;%n" +
                "- enter 0 to exit.%n");

        do {
            System.out.print("Enter a request: ");
            input = scanner.next();
            if(!input.matches("\\d+")) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if ("0".equals(input)) {
                System.out.println("");
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                number = new BigInteger(input);
                properties(number);
            }
        } while (!"0".equals(input));
    }

    private static void properties(BigInteger number) {
        System.out.printf("Properties of %s%n", number.toString());
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindromic(number));
    }


    private static boolean isOdd(BigInteger number) {
        return !number.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO);
    }

    private static boolean isEven(BigInteger number) {
        return number.remainder(BigInteger.TWO).equals(BigInteger.ZERO);
    }

    private static boolean isBuzz(BigInteger number) {
        return number.remainder(BigInteger.TEN).equals(buzzNumber) ||
                number.remainder(buzzNumber).equals(BigInteger.ZERO) ||
                number.equals(buzzNumber);
    }

    private static boolean isDuck(BigInteger number) {
        return String.valueOf(number).lastIndexOf('0') > 0;
    }

    private static boolean isPalindromic(BigInteger number) {
        StringBuilder palindromic = new StringBuilder(String.valueOf(number));
        BigInteger mirror = new BigInteger(String.valueOf(palindromic.reverse()));
        return number.equals(mirror);
    }
}
