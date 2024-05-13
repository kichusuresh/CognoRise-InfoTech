package Calculator;
import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the two numbers to perform operations");
            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();
            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();
            System.out.println("Enter the operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            try {
                double result;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero is not allowed.");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("Invalid operator.");
                        continue; 
                }

                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            System.out.println("Do you want to perform another calculation? (yes/no)");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("yes")) {
                break; 
            }
        }
        scanner.close();
    }
}
	   
	
		   



	
