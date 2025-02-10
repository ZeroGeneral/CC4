package queueimplementation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Object> queue = new LinkedList<>(); // Generic Queue
        int choice;

        do {
            System.out.println("\n1. ENQUEUE");
            System.out.println("2. DEQUEUE");
            System.out.println("3. Display Queue");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter value to ENQUEUE (int, char, string): ");
                    String input = sc.nextLine();
                    Object value = detectType(input);
                    queue.add(value);
                    System.out.println(value + " enqueued into queue.");
                    break;
                case 2:
                    if (!queue.isEmpty()) {
                        System.out.println("Dequeued: " + queue.poll());
                    } else {
                        System.out.println("Queue is empty. Nothing to DEQUEUE.");
                    }
                    break;
                case 3:
                    displayQueue(queue);
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

    // Method to display queue with index
    private static void displayQueue(Queue<Object> queue) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Queue contents:");
            int index = 0;
            for (Object item : queue) {
                System.out.println("Index " + index + ": " + item);
                index++;
            }
        }
    }
}
