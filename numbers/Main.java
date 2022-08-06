package numbers;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    final private static BigInteger buzzNumber = new BigInteger("7");

    final private static List<String> propertiesList = Arrays.asList("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY");

    public static void main(String[] args) {
        System.out.printf("Welcome to Amazing Numbers!%n%n");
        input();
    }

    private static void input() {
        List<String> input;
        printInstruction();
        do {
            System.out.print("Enter a request: ");
            input = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
            // if input not contain any numbers;
            if (input.isEmpty()) {
                printInstruction();
            }
            // if input contain one numbers;
            if (input.size() == 1) {
                if ("0".equals(input.get(0))) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                } else if (input.get(0).matches("\\d+")) {
                    // and it isn't zero:
                    properties(input.get(0));
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            // if input contain two parts;
            if (input.size() == 2) {
                if (!input.get(0).matches("\\d+")) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (!input.get(1).matches("\\d+")) {
                    System.out.println("The second parameter should be a natural number.");
                } else {
                    properties(input.get(0), input.get(1));
                }
            }
            // if input contain three parts;
            if (input.size() == 3) {
                if (!input.get(0).matches("\\d+")) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (!input.get(1).matches("\\d+")) {
                    System.out.println("The second parameter should be a natural number.");
                } else if (!propertiesList.contains(input.get(2).toUpperCase())) {
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]%n", input.get(2).toUpperCase());
                } else {
                    properties(input.get(0), input.get(1), input.get(2).toLowerCase());
                }
            }
            // if input contain four parts;
            if (input.size() == 4) {
                if (!input.get(0).matches("\\d+")) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (!input.get(1).matches("\\d+")) {
                    System.out.println("The second parameter should be a natural number.");
                } else if (!propertiesList.contains(input.get(2).toUpperCase()) && !propertiesList.contains(input.get(3).toUpperCase())) {
                    System.out.printf("The properties [%s, %s] are wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]%n", input.get(2).toUpperCase(), input.get(3).toUpperCase());
                } else if (!propertiesList.contains(input.get(2).toUpperCase())) {
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]%n", input.get(2).toUpperCase());
                } else if (!propertiesList.contains(input.get(3).toUpperCase())) {
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]%n", input.get(3).toUpperCase());
                } else if (!mutuallyExclusiveProperties(input)) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]%n" +
                            "There are no numbers with these properties.%n", input.get(2).toUpperCase(), input.get(3).toUpperCase());
                } else {
                    properties(input.get(0), input.get(1), input.get(2).toLowerCase(), input.get(3).toLowerCase());
                }
            }

        } while (!"0".equals(input.get(0)));
    }

    private static boolean mutuallyExclusiveProperties(List<String> input) {
        boolean check = true;

        if ("even".equalsIgnoreCase(input.get(2)) && "odd".equalsIgnoreCase(input.get(3)) ||
                "odd".equalsIgnoreCase(input.get(2)) && "even".equalsIgnoreCase(input.get(3))) {
            check = false;
        }

        if ("duck".equalsIgnoreCase(input.get(2)) && "spy".equalsIgnoreCase(input.get(3)) ||
                "spy".equalsIgnoreCase(input.get(2)) && "duck".equalsIgnoreCase(input.get(3))) {
            check = false;
        }
        if ("sunny".equalsIgnoreCase(input.get(2)) && "square".equalsIgnoreCase(input.get(3)) ||
                "square".equalsIgnoreCase(input.get(2)) && "sunny".equalsIgnoreCase(input.get(3))) {
            check = false;
        }
        return check;
    }

    private static void printInstruction() {
        System.out.printf("Supported requests:%n" +
                "- enter a natural number to know its properties;%n" +
                "- enter two natural numbers to obtain the properties of the list:%n" +
                " * the first parameter represents a starting number;%n" +
                " * the second parameter shows how many consecutive numbers are to be printed;%n" +
                "- two natural numbers and a property to search for;" +
                "- two natural numbers and two properties to search for;" +
                "- separate the parameters with one space;%n" +
                "- enter 0 to exit.%n%n");
    }

    private static void properties(String first) {
        BigInteger a = new BigInteger(first);

        System.out.printf("Properties of %d%n", a);
        System.out.printf("buzz : %s%n", checkTrueFalse(isBuzz(a)));
        System.out.printf("duck : %s%n", checkTrueFalse(isDuck(a)));
        System.out.printf("palindromic : %s%n", checkTrueFalse(isPalindromic(a)));
        System.out.printf("gapful : %s%n", checkTrueFalse(isGapfulNumbers(a)));
        System.out.printf("spy : %s%n", checkTrueFalse(isSpyNumber(a)));
        /*System.out.printf("square : %s%n", checkTrueFalse(isSquare(a)));
        System.out.printf("sunny : %s%n", checkTrueFalse(isSunny(a)));*/
        System.out.printf("even : %s%n", checkTrueFalse(isEven(a)));
        System.out.printf("odd : %s%n", checkTrueFalse(isOdd(a)));
    }
    private static void properties(String first, String second) {
        BigInteger a = new BigInteger(first);
        int b = Integer.parseInt(second);
        BigInteger count = BigInteger.ZERO;
        BigInteger operator = a;
        for (int i = 0; i < b; i++) {
            List<String> properties = new ArrayList<>();
            properties.add(isBuzz(operator.add(count)));
            properties.add(isDuck(operator.add(count)));
            properties.add(isPalindromic(operator.add(count)));
            properties.add(isGapfulNumbers(operator.add(count)));
            properties.add(isSpyNumber(operator.add(count)));
           /* properties.add(isSquare(operator.add(count)));
            properties.add(isSunny(operator.add(count)));*/
            properties.add(isEven(operator.add(count)));
            properties.add(isOdd(operator.add(count)));

            System.out.printf("%s is", operator.add(count) );
            for (String property : properties) {
                if (!property.isEmpty()) {
                    System.out.printf(" %s,", property);
                }
            }
            System.out.printf("%n");
            count = count.add(BigInteger.ONE);
        }
    }

    private static void properties(String number, String amount, String property1) {
        long a = Long.parseLong(number);
        int i = 0;
        do {
            if (isByProperty(a, property1).equals(property1)) {
                properties(String.valueOf(a), "1");
                i++;
            }
            a++;
        } while (i < Integer.parseInt(amount));
    }

    private static void properties(String number, String amount, String property1, String property2) {
        long a = Long.parseLong(number);
        int i = 0;
        do {
            if (isByProperty(a, property1).equals(property1) && isByProperty(a, property2).equals(property2)) {
                properties(String.valueOf(a), "1");
                i++;
            }
            a++;
        } while (i < Integer.parseInt(amount));
    }

    private static String isOdd(BigInteger a) {
        return !a.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO) || a.equals(BigInteger.ONE) ? "odd" : "";
    }

    private static String isEven(BigInteger a) {
        return a.remainder(BigInteger.TWO).equals(BigInteger.ZERO) ? "even" : "";
    }

    private static String isBuzz(BigInteger a) {
        String check = "";
        if (a.remainder(BigInteger.TEN).equals(buzzNumber) ||
                a.remainder(buzzNumber).equals(BigInteger.ZERO) ||
                a.equals(buzzNumber)) {
            check = "buzz";
        }
        return check;
    }

    private static String isDuck(BigInteger a) {
        return String.valueOf(a).lastIndexOf('0') > 0 ? "duck" : "";
    }

    private static String isPalindromic(BigInteger a) {
        String check = "";
        StringBuilder palindromic = new StringBuilder(String.valueOf(a));
        BigInteger mirror = new BigInteger(String.valueOf(palindromic.reverse()));
        return a.equals(mirror) ? "palindromic" : "";
    }

    private static String isGapfulNumbers(BigInteger a) {
        StringBuilder gapful = new StringBuilder(String.valueOf(a));
        BigInteger firstLast = new BigInteger((String.valueOf(gapful.charAt(0)) + String.valueOf(gapful.charAt(gapful.length() - 1))));
        String check = (gapful.length() > 2 && Objects.equals(a.remainder(firstLast), BigInteger.ZERO)) ? "gapful" : "";
        return check;
    }

    private static String isSpyNumber(BigInteger a) {
        int sum = 0;
        int product = 1;
        StringBuilder spy = new StringBuilder(String.valueOf(a));
        for (int i = 0; i < spy.length(); i++) {
            sum += Integer.parseInt(String.valueOf(spy.charAt(i)));
            product *= Integer.parseInt(String.valueOf(spy.charAt(i)));
        }
        return sum == product ? "spy" : "";
    }

    private static String isSunny(BigInteger a) {
        long sunny = Long.parseLong(a.toString()) + 1;
        return "square".equals(isSquare(BigInteger.valueOf(sunny))) ? "sunny" : "" ;
    }

    private static String isSquare(BigInteger a) {
        long sunny = Long.parseLong(a.toString());
        return Math.sqrt((double) sunny) * 10 % 10  == 0 ? "square" : "" ;
    }

    public static String checkTrueFalse(String a) {
        return !a.isEmpty() ? "true" : "false";
    }

    public static String isByProperty(Long number, String property) {
        BigInteger nameBig = new BigInteger(String.valueOf(number));
        String answer = "";
        switch (property.toLowerCase()) {
            case "even" : answer = isEven(nameBig);
                break;
            case "odd" :  answer = isOdd(nameBig);
                break;
            case "buzz" :  answer = isBuzz(nameBig);
                break;
            case "duck" :  answer = isDuck(nameBig);
                break;
            case "palindromic" :  answer = isPalindromic(nameBig);
                break;
            case "gapful" : answer = isGapfulNumbers(nameBig);
                break;
            case "spy" : answer = isSpyNumber(nameBig);
                break;
            case "sunny" : answer =isSunny(nameBig);
                break;
            case "square" : answer = isSquare(nameBig);
                break;
            default:
                break;
        }
        return answer;
    }
}
