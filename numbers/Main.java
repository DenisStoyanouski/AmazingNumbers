package numbers;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    final private static int buzzNumber = 7;

    public static void main(String[] args) {
        input();
    }

    private static void input() {
        String input;
        int number = 0;
        boolean naturalNumber = true;
        //do {
            System.out.println("Enter a natural number: ");
            input = scanner.next();
            if(!input.matches("\\d+") || "0".equals(input)) {
                System.out.println("This number is not natural!");
                naturalNumber = false;
            } else {
                number = Integer.parseInt(input);
                properties(number);
            }
        //} while (!naturalNumber);

    }

    private static void properties(int number) {
        System.out.printf("Properties of %d%n", number);
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
    }


    private static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static boolean isBuzz(int number) {
        return number % 10 == buzzNumber || number % buzzNumber == 0 || number == buzzNumber;

    }

    private static boolean isDuck(int number) {
        return String.valueOf(number).lastIndexOf('0') > 0;
    }
}
