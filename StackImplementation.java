package stackimplementation;

import java.util.Scanner;
import java.util.Stack;

public class StackImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Object> stack = new Stack<>(); // Generic Stack
        int choice;

        do {
            System.out.println("\n1. PUSH");
            System.out.println("2. POP");
            System.out.println("3. Display Stack");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter value to PUSH (int, char, string): ");
                    String input = sc.nextLine();
                    Object value = detectType(input);
                    stack.push(value);
                    System.out.println(value + " pushed into stack.");
                    break;
                case 2:
                    if (!stack.isEmpty()) {
                        System.out.println("Popped: " + stack.pop());
                    } else {
                        System.out.println("Stack is empty. Nothing to POP.");
                    }
                    break;
                case 3:
                    displayStack(stack);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    // Method to detect and convert input to Integer, Character, or String
    private static Object detectType(String input) {
        if (input.matches("-?\\d+")) return Integer.parseInt(input);
        else if (input.length() == 1) return input.charAt(0);
        return input;
    }

    // Method to display stack with index
    private static void displayStack(Stack<Object> stack) {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.println("Stack contents:");
            for (int i = 0; i < stack.size(); i++) {
                System.out.println("Index " + i + ": " + stack.get(i));
            }
        }
    }
}
