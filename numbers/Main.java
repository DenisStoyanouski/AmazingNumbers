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
                checkingOddEven(number);
            }
        //} while (!naturalNumber);

    }

    private static void checkingOddEven(int number) {
        if (number % 2 == 0) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }
        checkingBuzz(number);
    }

    private static void checkingBuzz(int number) {
        boolean divisibleBySeven = false;
        boolean endWithSeven = false;

        if ((number % 10) == buzzNumber) {
            endWithSeven = true;
        }
        if (number % buzzNumber == 0 || number == buzzNumber) {
            divisibleBySeven = true;
        }

        if (divisibleBySeven && endWithSeven) {
            System.out.println("It is a Buzz number.");
            System.out.printf("Explanation:%n%d is divisible by 7 and ends with 7.%n", number);
        } else if (divisibleBySeven) {
            System.out.println("It is a Buzz number.");
            System.out.printf("Explanation:%n%d is divisible by 7.%n", number);
        } else if (endWithSeven) {
            System.out.println("It is a Buzz number.");
            System.out.printf("Explanation:%n%d ends with 7.%n", number);
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.printf("Explanation:%n%d is neither divisible by 7 nor does it end with 7.%n", number);

        }



    }
}
