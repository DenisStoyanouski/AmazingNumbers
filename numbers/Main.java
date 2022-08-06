package numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    final private static BigInteger buzzNumber = new BigInteger("7");

    final private static List<String> propertiesList = Arrays.asList("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY","JUMPING");

    public static void main(String[] args) {
        System.out.printf("Welcome to Amazing Numbers!%n%n");
        input();
    }

    private static void input() {
        List<String> input;
        printInstruction();
        do {
            System.out.print("Enter a request: ");
            input = Arrays.stream(scanner.nextLine().trim().split("\\s+")).collect(Collectors.toList());
            List<String> inputProperties = new ArrayList<>();
            for (String x : input) {
                if (input.indexOf(x) > 1) {
                    inputProperties.add(x.toUpperCase());
                }
            }

            // if input not contain any numbers;
            if (input.isEmpty()) {
                printInstruction();
            }
            // if input contain one numbers;
            if (input.size() == 1 && checkFirstParameter(input)) {
                properties(input.get(0));
            }
            // if input contain two parts;
            if (input.size() == 2 && checkFirstParameter(input) && checkSecondParameter(input)) {
                properties(input.get(0), input.get(1));
            }
            // if input contain three parts and more;
            if (input.size() == 3 && checkFirstParameter(input) && checkSecondParameter(input) && checkPropertyList(inputProperties)) {
                properties(input.get(0), input.get(1), inputProperties);
            }
            // if input contain four parts;
            if (input.size() > 3) {
                if(checkFirstParameter(input) && checkSecondParameter(input) && checkPropertyList(inputProperties) &&
                        mutuallyExclusiveProperties(inputProperties)) {
                    propertiesAll(input.get(0), input.get(1), inputProperties);
                }

            }

        } while (!"0".equals(input.get(0)));
    }

    private static boolean checkFirstParameter(List<String> input) {
        boolean check = true;
        if ("0".equals(input.get(0))) {
            check = false;
            System.out.println("Goodbye!");
            System.exit(0);
        } else if (!input.get(0).matches("\\d+")) {
            check = false;
            System.out.println("The first parameter should be a natural number or zero.");
        }
        return check;
    }

    private static boolean checkSecondParameter(List<String> input) {
        boolean check = true;
        if (!input.get(1).matches("\\d+")) {
            check = false;
            System.out.println("The second parameter should be a natural number.");
        }
        return check;
    }

    private static boolean checkPropertyList(List<String> inputProperties) {
    boolean check = true;
        List<String> blackList = new ArrayList<>();
        for (String property : inputProperties) {
            if (!propertiesList.contains(property)) {
                blackList.add(property);
            }
        }
        if (blackList.size() == 1) {
            check = false;
            System.out.printf("The property %s is wrong.\n" +
                    "Available properties: %s%n", blackList, propertiesList);
        } else if (blackList.size() > 1) {
            check = false;
            System.out.printf("The properties %s are wrong.\n" +
                    "Available properties: %s%n", blackList, propertiesList);
        }
        return check;
    }

    private static boolean mutuallyExclusiveProperties(List<String> inputProperties) {
        boolean check = true;

        if (inputProperties.contains("ODD") && inputProperties.contains("EVEN")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                    "There are no numbers with these properties.");
            check = false;
        }
        if (inputProperties.contains("DUCK") && inputProperties.contains("SPY")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                    "There are no numbers with these properties.");
            check = false;
        }
        if (inputProperties.contains("SUNNY") && inputProperties.contains("SQUARE")) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]\n" +
                    "There are no numbers with these properties.");
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
                "- two natural numbers and two properties to search for;%n" +
                "- separate the parameters with one space;%n" +
                "- enter 0 to exit.%n%n");
    }

    private static void properties(String parameter1st) {

        BigInteger a = new BigInteger(parameter1st);
        System.out.printf("Properties of %s%n", String.format(Locale.US, "%,d", a));
        System.out.printf("buzz : %s%n", checkTrueFalse(isBuzz(parameter1st)));
        System.out.printf("duck : %s%n", checkTrueFalse(isDuck(parameter1st)));
        System.out.printf("palindromic : %s%n", checkTrueFalse(isPalindromic(parameter1st)));
        System.out.printf("gapful : %s%n", checkTrueFalse(isGapfulNumbers(parameter1st)));
        System.out.printf("spy : %s%n", checkTrueFalse(isSpyNumber(parameter1st)));
        System.out.printf("square : %s%n", checkTrueFalse(isSquare(parameter1st)));
        System.out.printf("sunny : %s%n", checkTrueFalse(isSunny(parameter1st)));
        System.out.printf("jumping : %s%n", checkTrueFalse(isJumping(parameter1st)));
        System.out.printf("even : %s%n", checkTrueFalse(isEven(parameter1st)));
        System.out.printf("odd : %s%n", checkTrueFalse(isOdd(parameter1st)));
    }

    private static void properties(String first, String second) {
        BigInteger a = new BigInteger(first);
        int b = Integer.parseInt(second);
        BigInteger count = BigInteger.ZERO;
        BigInteger operator = a;
            for (int i = 0; i < b; i++) {
                List<String> properties = new ArrayList<>();
                properties.add(isBuzz(String.valueOf(operator.add(count))));
                properties.add(isDuck(String.valueOf(operator.add(count))));
                properties.add(isPalindromic(String.valueOf(operator.add(count))));
                properties.add(isGapfulNumbers(String.valueOf(operator.add(count))));
                properties.add(isSpyNumber(String.valueOf(operator.add(count))));
                properties.add(isSquare(String.valueOf(operator.add(count))));
                properties.add(isSunny(String.valueOf(operator.add(count))));
                properties.add(isJumping(String.valueOf(operator.add(count))));
                properties.add(isEven(String.valueOf(operator.add(count))));
                properties.add(isOdd(String.valueOf(operator.add(count))));

                System.out.printf("%s is", String.format(Locale.US, "%,d", operator.add(count)));
                for (String property : properties) {
                    if (!property.isEmpty()) {
                        System.out.printf(" %s,", property.toLowerCase());
                    }
                }
                System.out.printf("%n");
                count = count.add(BigInteger.ONE);
            }
    }

    private static void properties(String number, String amount, List<String> inputProperties) {
        BigInteger a = new BigInteger(number);
        for (String property : inputProperties) {
            int i = 0;
            do {
                if (isByProperty(String.valueOf(a), property).equals(property)) {
                    properties(String.valueOf(a), "1");
                    i++;
                }
                a = a.add(BigInteger.ONE);
            } while (i < Integer.parseInt(amount));
        }
    }

    private static void propertiesAll(String number, String amount, List<String> inputProperties) {
        BigInteger a = new BigInteger(number);
        int i = 0;
        do {
            if (isListOfProperty(String.valueOf(a), inputProperties)) {
                properties(String.valueOf(a), "1");
                i++;
            }
            a = a.add(BigInteger.ONE);
        } while (i < Integer.parseInt(amount));
    }

    private static String isOdd(String a) {
        BigInteger number = new BigInteger(a);
        return !number.remainder(BigInteger.TWO).equals(BigInteger.ZERO) || number.equals(BigInteger.ZERO) ? "ODD" : "";
    }

    private static String isEven(String a) {
        BigInteger number = new BigInteger(a);
        return number.remainder(BigInteger.TWO).equals(BigInteger.ZERO) ? "EVEN" : "";
    }

    private static String isBuzz(String a) {
        BigInteger number = new BigInteger(a);
        String check = "";
        if (number.remainder(BigInteger.TEN).equals(buzzNumber) ||
                number.remainder(buzzNumber).equals(BigInteger.ZERO) || number.equals(buzzNumber)) {
            check = "BUZZ";
        }
        return check;
    }

    private static String isDuck(String a) {
        return String.valueOf(a).lastIndexOf('0') > 0 ? "DUCK" : "";
    }

    private static String isPalindromic(String a) {
        StringBuilder palindromic = new StringBuilder(a);
        String mirror = palindromic.reverse().toString();
        return a.equals(mirror) ? "PALINDROMIC" : "";
    }

    private static String isGapfulNumbers(String a) {
        String firstLast = (a.charAt(0) + String.valueOf(a.charAt(a.length() - 1)));
        BigInteger number = new BigInteger(a);
        BigInteger gapful = new BigInteger(firstLast);

        return (a.length() > 2 && number.remainder(gapful).equals(BigInteger.ZERO)) ? "GAPFUL" : "";
    }

    private static String isSpyNumber(String a) {
        int sum = 0;
        int product = 1;
        StringBuilder spy = new StringBuilder(a);
        for (int i = 0; i < spy.length(); i++) {
            sum += Integer.parseInt(String.valueOf(spy.charAt(i)));
            product *= Integer.parseInt(String.valueOf(spy.charAt(i)));
        }
        return sum == product ? "SPY" : "";
    }

    private static String isSunny(String a) {
        BigInteger number = new BigInteger(a);
        return "SQUARE".equals(isSquare(String.valueOf(number.add(BigInteger.ONE)))) ? "SUNNY" : "" ;
    }

    private static String isSquare(String a) {
        BigDecimal number = new BigDecimal(a);
        MathContext mc = new MathContext(5);
        return Objects.equals(number.sqrt(mc).setScale(0, RoundingMode.DOWN).pow(2), number) ? "SQUARE" : "" ;
    }

    private static String isJumping(String a) {
        boolean check = true;
        for (int i = 1; i < a.length(); i++) {
            if ((a.charAt(i) + 1 != a.charAt(i - 1) - 0) && (a.charAt(i) - 1 != a.charAt(i - 1) - 0)) {
                check = false;
                break;
            }
        }
        return check ? "JUMPING" : "";
    }

    public static String checkTrueFalse(String a) {
        return !a.isEmpty() ? "true" : "false";
    }

    public static String isByProperty(String number, String property) {
        String answer = "";
        switch (property) {
            case "EVEN" : answer = isEven(number);
                break;
            case "ODD" :  answer = isOdd(number);
                break;
            case "BUZZ" :  answer = isBuzz(number);
                break;
            case "DUCK" :  answer = isDuck(number);
                break;
            case "PALINDROMIC" :  answer = isPalindromic(number);
                break;
            case "GAPFUL" : answer = isGapfulNumbers(number);
                break;
            case "SPY" : answer = isSpyNumber(number);
                break;
            case "SUNNY" : answer =isSunny(number);
                break;
            case "SQUARE" : answer = isSquare(number);
                break;
            case "JUMPING" : answer = isJumping(number);
                break;
            default:
                break;
        }
        return answer;
    }

    public static boolean isListOfProperty(String number, List<String> inputProperties) {
        List<String> checker = new ArrayList<>();
        for (String property : inputProperties) {
            if (isByProperty(number, property).equals(property)) {
            checker.add(property);
            }
        }
        return checker.equals(inputProperties);
    }
}
