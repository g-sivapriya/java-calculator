import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> storedResults = new ArrayList<>();
        System.out.println("Advanced Java Calculator with multi-result operations");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1: Perform a calculation and store result");
            System.out.println("2: Operate on multiple stored results");
            System.out.println("3: View stored results");
            System.out.println("4: Clear stored results");
            System.out.println("5: Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter first number: ");
                    double num1 = scanner.nextDouble();
                    System.out.print("Enter operator (+, -, *, /, %, sqrt, sin, cos, tan): ");
                    String operator = scanner.next();
                    double num2 = 0;
                    if (operator.matches("[+\\-*/%]")) {
                        System.out.print("Enter second number: ");
                        num2 = scanner.nextDouble();
                    }

                    double result;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                System.out.println("Division by zero error!");
                                continue;
                            }
                            result = num1 / num2;
                            break;
                        case "%":
                            result = num1 % num2;
                            break;
                        case "sqrt":
                            if (num1 < 0) {
                                System.out.println("Square root of negative number error!");
                                continue;
                            }
                            result = Math.sqrt(num1);
                            break;
                        case "sin":
                            result = Math.sin(Math.toRadians(num1));
                            break;
                        case "cos":
                            result = Math.cos(Math.toRadians(num1));
                            break;
                        case "tan":
                            result = Math.tan(Math.toRadians(num1));
                            break;
                        default:
                            System.out.println("Invalid operator!");
                            continue;
                    }

                    storedResults.add(result);
                    System.out.println("Result stored: " + result);
                    break;

                case 2:
                    if (storedResults.size() < 2) {
                        System.out.println("You need at least two stored results to perform this operation.");
                        break;
                    }

                    System.out.println("Stored results:");
                    for (int i = 0; i < storedResults.size(); i++) {
                        System.out.println((i + 1) + ": " + storedResults.get(i));
                    }
                    System.out.print("Enter the quantity of results to use (e.g., 3 for three results): ");
                    int n = scanner.nextInt();
                    if (n < 2 || n > storedResults.size()) {
                        System.out.println("Invalid number of results selected.");
                        break;
                    }

                    int[] indices = new int[n];
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter index of result " + (i + 1) + ": ");
                        int idx = scanner.nextInt() - 1;
                        if (idx < 0 || idx >= storedResults.size()) {
                            System.out.println("Invalid index.");
                            i--; // ask for this index again
                        } else {
                            indices[i] = idx;
                        }
                    }

                    System.out.print("Enter operator to apply across all selected results (+, -, *, /, %): ");
                    String op = scanner.next();

                    Double combinedResult = storedResults.get(indices[0]);
                    boolean valid = true;
                    for (int i = 1; i < n; i++) {
                        double nextVal = storedResults.get(indices[i]);
                        switch (op) {
                            case "+":
                                combinedResult += nextVal;
                                break;
                            case "-":
                                combinedResult -= nextVal;
                                break;
                            case "*":
                                combinedResult *= nextVal;
                                break;
                            case "/":
                                if (nextVal == 0) {
                                    System.out.println("Division by zero error during operation!");
                                    valid = false;
                                } else {
                                    combinedResult /= nextVal;
                                }
                                break;
                            case "%":
                                combinedResult %= nextVal;
                                break;
                            default:
                                System.out.println("Invalid operator!");
                                valid = false;
                                break;
                        }
                        if (!valid) break;
                    }
                    if (valid) {
                        storedResults.add(combinedResult);
                        System.out.println("Operation result stored: " + combinedResult);
                    }
                    break;

                case 3:
                    if (storedResults.isEmpty()) {
                        System.out.println("No stored results.");
                    } else {
                        System.out.println("Stored results:");
                        for (int i = 0; i < storedResults.size(); i++) {
                            System.out.println((i + 1) + ": " + storedResults.get(i));
                        }
                    }
                    break;

                case 4:
                    storedResults.clear();
                    System.out.println("Stored results cleared.");
                    break;

                case 5:
                    System.out.println("Exiting calculator.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}