import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter first number, operator and second number");
        input = scanner.nextLine();
        String output = calc(input);
        System.out.println(output);
    }
    public static String calc(String input) {
        String[] str = input.split(" ");
        if (str.length > 3) {
            throw new IllegalArgumentException("You can enter only one operation");
        }
        char operator = str[1].charAt(0);
        int firstNumber;
        int secondNumber;
        boolean isRoman;
        if (str[0].matches("[IVXLCDM]+") && str[2].matches("[IVXLCDM]+")) {
            isRoman = true;
            firstNumber = toArabic(str[0]);
            secondNumber = toArabic(str[2]);
        } else if (str[0].matches("\\d+") && str[2].matches("\\d+")) {
            isRoman = false;
            firstNumber = Integer.parseInt(str[0]);
            secondNumber = Integer.parseInt(str[2]);
        } else {
            throw new IllegalArgumentException("You can use two arabic or two roman numbers, not one arabic number and one roman number");
        }
        String result = "" + operation(firstNumber, secondNumber, operator);
        if (isRoman == false) {
            return result;
        } else {
            int value = Integer.valueOf(result);
            return toRoman(value);
        }
    }
    public static int toArabic (String romanNumber){
        switch (romanNumber) {
            case "I":
                return 1;
            case "V":
                return 5;
            case "X":
                return 10;
            case "L":
                return 50;
            case "C":
                return 100;
            case "D":
                return 500;
            case "M":
                return 1000;
            default:
                throw new IllegalArgumentException("It's not a correct roman number");
            }
        }
    public static String toRoman ( int number){
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Invalid roman number");
        }
        String[] romanNumbers = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicNumbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder romanResult = new StringBuilder();
        for (int i = 0; i < arabicNumbers.length; i++) {
            while (number >= arabicNumbers[i]) {
                romanResult.append(romanNumbers[i]);
                number -= arabicNumbers[i];
            }
        }
        return romanResult.toString();
    }
    public static int operation ( int firstNumber, int secondNumber, char operator){
        return switch (operator) {
            case '+' -> firstNumber + secondNumber;
            case '-' -> firstNumber - secondNumber;
            case '*' -> firstNumber * secondNumber;
            case '/' -> firstNumber / secondNumber;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }
}