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

    public enum Properties {

        EVEN(false, true),
        ODD(false, true),
        BUZZ(false, true),
        DUCK(false, true),
        PALINDROMIC(false, true),
        GAPFUL(false, true),
        SPY(false, true),
        SQUARE(false, true),
        SUNNY(false, true),
        JUMPING(false, true),
        HAPPY(false, true),
        SAD(false, true);

        boolean statement;
        boolean exclude;

        Properties(boolean statement, boolean exclude) {
            this.exclude = exclude;
            this.statement = statement;
        }

        public void setStatement(boolean statement) {
            this.statement = statement;
        }

        public void setExclude(boolean exclude) {
            this.exclude = exclude;
        }
    }

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
                properties(input.get(0), inputProperties);
            }
            // if input contain two parts;
            if (input.size() == 2 && checkFirstParameter(input) && checkSecondParameter(input)) {
                properties(input.get(0), input.get(1), inputProperties);
            }
            // if input contain four parts;
            if (input.size() > 2) {
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
        List<String> blackList = new ArrayList<>(inputProperties);
        List<String> whiteList = new ArrayList<>();
        for (Properties p : Properties.values()) {
            whiteList.add(p.name());
        }
        blackList.removeIf(x-> whiteList.contains(x.replace("-", "")));
        if (blackList.size() == 1) {
            check = false;
            System.out.printf("The property %s is wrong.\n" +
                    "Available properties: %s%n", blackList, Arrays.toString(Properties.values()));
        } else if (blackList.size() > 1) {
            check = false;
            System.out.printf("The properties %s are wrong.\n" +
                    "Available properties: %s%n", blackList, Arrays.toString(Properties.values()));
        }
        return check;
    }

    private static boolean mutuallyExclusiveProperties(List<String> inputProperties) {
        boolean check = true;
        List<List<String>> listsOfMutually = List.of(
                List.of("ODD","EVEN"), List.of("DUCK","SPY"), List.of("SUNNY","SQUARE"), List.of("HAPPY","SAD"),
                List.of("-ODD","-EVEN"),List.of("-DUCK","-SPY"),  List.of("-HAPPY","-SAD"),
                List.of("ODD","-ODD"),List.of("DUCK","-DUCK"), List.of("SUNNY","-SUNNY"), List.of("HAPPY","-HAPPY"),
                List.of("EVEN","-EVEN"),List.of("SPY","-SPY"), List.of("SQUARE","-SQUARE"), List.of("SAD","-SAD"));
        for (List<String> list : listsOfMutually) {
            if (inputProperties.containsAll(list)) {
                System.out.printf("The request contains mutually exclusive properties: %s\n" +
                        "There are no numbers with these properties.%n", list);
                check = false;
            }
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
                "- a property preceded by minus must not be present in numbers;%n" +
                "- separate the parameters with one space;%n" +
                "- enter 0 to exit.%n%n");
    }

    private static void properties(String parameter1st, List<String> propertiesOfNumber) {
        BigInteger a = new BigInteger(parameter1st);
        if (propertiesOfNumber.size() == 0) {
            for (Properties p : Properties.values()) {
                propertiesOfNumber.add(p.name());
            }
        }
        System.out.printf("Properties of %s%n", String.format(Locale.US, "%,d", a));
        for (Properties property : Properties.values()) {
            invokeCheckByPropertyName(parameter1st, property.name());
            System.out.printf("%s : %s%n", property.name().toLowerCase(), property.statement);
        }
    }

    private static void properties(String parameter1st, String second, List<String> propertiesOfNumber) {
        BigInteger a = new BigInteger(parameter1st);
        int b = Integer.parseInt(second);

        if (propertiesOfNumber.size() == 0) {
            for (Properties p : Properties.values()) {
                propertiesOfNumber.add(p.name());
            }
        }

        for (int i = 0; i < b; i++) {
            System.out.printf("%s is", String.format(Locale.US, "%,d", a));
            for (Properties property : Properties.values()) {
                invokeCheckByPropertyName(a.toString(), property.name());
            }
            for ( Properties property : Properties.values()) {
                if (property.statement) {
                    System.out.printf(" %s,", property.name().toLowerCase());
                }
            }
            System.out.println();
            a = a.add(BigInteger.ONE);
        }
    }

    private static void propertiesAll(String number, String amount, List<String> propertiesOfNumber) {
        BigInteger a = new BigInteger(number);
        List<String> currentProperties = new ArrayList<>(propertiesOfNumber);
        int i = 0;
        List<String> trueStatementList = new ArrayList<>();

        //create exclude statement list
        currentProperties.removeIf(x-> !x.contains("-"));
        ArrayList<String> excludeStatementList = new ArrayList<>();
        for (String property : currentProperties) {
            String pro = property.replace("-","");
            excludeStatementList.add(pro);
        }
        if (excludeStatementList.isEmpty()) {
            excludeStatementList.add(" ");
        }

        //create all truth statement list
        List<String> truthProperties = new ArrayList<>();
        for (String property : propertiesOfNumber) {
            if (property.indexOf('-') != 0) {
                truthProperties.add(property);
            }
        }

        long counter = 0;
        do {
            String iterator = a.add(BigInteger.valueOf(counter)).toString();
            //check all properties of number and switch properties statements in Properties;
            for (Properties property: Properties.values()) {
                invokeCheckByPropertyName(iterator, property.name());
                if (property.statement) {
                    trueStatementList.add(property.name());
                }
            }

            if (trueStatementList.stream().noneMatch(excludeStatementList::contains) && trueStatementList.containsAll(truthProperties)) {
                BigInteger b = new BigInteger(iterator);
                System.out.printf("%s is", String.format(Locale.US, "%,d", b));
                // print all properties that have statement true;
                for (Properties property: Properties.values()) {
                    if (property.statement) {
                        System.out.printf(" %s,", property.name().toLowerCase());
                    }
                }
                System.out.println("");
                i++;
            }
            counter++;
            trueStatementList.clear();
        } while (i < Integer.parseInt(amount));
    }

    private static void isOdd(String number) {
        BigInteger a = new BigInteger(number);
        Properties.ODD.setStatement(!a.remainder(BigInteger.TWO).equals(BigInteger.ZERO) || a.equals(BigInteger.ZERO));
    }

    private static void isEven(String number) {
        BigInteger a = new BigInteger(number);
        Properties.EVEN.setStatement(a.remainder(BigInteger.TWO).equals(BigInteger.ZERO));
    }

    private static void isBuzz(String number) {
        BigInteger a = new BigInteger(number);
        Properties.BUZZ.setStatement(a.remainder(BigInteger.TEN).equals(buzzNumber) || a.remainder(buzzNumber).equals(BigInteger.ZERO) ||
                a.equals(buzzNumber));
    }

    private static void isDuck(String number) {
        Properties.DUCK.setStatement(String.valueOf(number).lastIndexOf('0') > 0);
    }

    private static void isPalindromic(String number) {
        StringBuilder palindromic = new StringBuilder(number);
        String mirror = palindromic.reverse().toString();
        Properties.PALINDROMIC.setStatement(number.equals(mirror));
    }

    private static void isGapful(String number) {
        // create number from first and last digits of the number;
        String firstLast = (number.charAt(0) + String.valueOf(number.charAt(number.length() - 1)));
        BigInteger a = new BigInteger(number);
        BigInteger gapful = new BigInteger(firstLast);

        Properties.GAPFUL.setStatement(number.length() > 2 && a.remainder(gapful).equals(BigInteger.ZERO));
    }

    private static void isSpy(String number) {
        int sumOfDigits= 0;
        int productOfDigits = 1;
        StringBuilder spy = new StringBuilder(number);
        // sum and product all digits of number;
        for (int i = 0; i < spy.length(); i++) {
            sumOfDigits += Integer.parseInt(String.valueOf(spy.charAt(i)));
            productOfDigits *= Integer.parseInt(String.valueOf(spy.charAt(i)));
        }

        Properties.SPY.setStatement(sumOfDigits == productOfDigits);
    }

    private static void isSunny(String number) {
        BigDecimal numberDec = new BigDecimal(number).add(BigDecimal.ONE);
        MathContext mc = new MathContext(5);
        BigDecimal sqrtOfNumber = numberDec.sqrt(mc).setScale(10, RoundingMode.DOWN);
        BigDecimal powerOfNumber = sqrtOfNumber.pow(2).setScale(10, RoundingMode.HALF_DOWN);
        if (Objects.equals(powerOfNumber, numberDec.setScale(10, RoundingMode.DOWN))) {
            Properties.SUNNY.setStatement(true);
        } else {
            Properties.SUNNY.setStatement(false);
        }
    }

    private static void isSquare(String number) {
        BigDecimal numberBig = new BigDecimal(number);
        MathContext mc = new MathContext(5);
        BigDecimal sqrtOfNumber = numberBig.sqrt(mc).setScale(10, RoundingMode.DOWN);
        BigDecimal powerOfNumber = sqrtOfNumber.pow(2).setScale(10, RoundingMode.HALF_DOWN);

        if (powerOfNumber.equals(numberBig.setScale(10, RoundingMode.DOWN))) {
            Properties.SQUARE.setStatement(true);
        } else {
            Properties.SQUARE.setStatement(false);
        }
    }

    private static void isJumping(String number) {
        boolean check = true;
        for (int i = 1; i < number.length(); i++) {
            if ((number.charAt(i) + 1 != number.charAt(i - 1) - 0) && (number.charAt(i) - 1 != number.charAt(i - 1) - 0)) {
                check = false;
                break;
            }
        }
        if (check) {
            Properties.JUMPING.setStatement(true);
        } else {
            Properties.JUMPING.setStatement(false);
        }
    }

    private static void isHappy(String number) {
        String a = number;
        do {
            int sum = 0;
            for (int i = 0; i < a.length(); i++) {
                sum += ((a.charAt(i) - '0') * (a.charAt(i) - '0'));
            }
            a = String.valueOf(sum);
        } while (a.length() != 1);

        Properties.HAPPY.setStatement(a.charAt(0) == '1');

    }

    private static void isSad(String number) {
        String a = number;
        do {
            int sum = 0;
            for (int i = 0; i < a.length(); i++) {
                sum += (a.charAt(i) - '0') * (a.charAt(i) - '0');
            }
            a = String.valueOf(sum);
        } while (a.length() != 1);

        Properties.SAD.setStatement(a.charAt(0) != '1');
    }

    private static void invokeCheckByPropertyName(String number, String property) {
        switch (property)  {
            case "EVEN" : isEven(number);
                break;
            case "ODD" :  isOdd(number);
                break;
            case "BUZZ" :  isBuzz(number);
                break;
            case "DUCK" :  isDuck(number);
                break;
            case "PALINDROMIC" :  isPalindromic(number);
                break;
            case "GAPFUL" : isGapful(number);
                break;
            case "SPY" : isSpy(number);
                break;
            case "SUNNY" : isSunny(number);
                break;
            case "SQUARE" : isSquare(number);
                break;
            case "JUMPING" : isJumping(number);
                break;
            case "HAPPY" : isHappy(number);
                break;
            case "SAD" : isSad(number);
                break;
            default:
                break;
        }
    }

}

