package avltree; // Ensure this matches your package structure

import java.util.*;

public class AVLTree {

    class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    private Node root;
    private List<Integer> arrayRepresentation = new ArrayList<>();

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(int key) {
        root = insert(root, key);
        updateArrayRepresentation();
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) return rightRotate(node);
        if (balance < -1 && key > node.right.key) return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int key) {
        root = delete(root, key);
        updateArrayRepresentation();
    }

    private Node delete(Node root, int key) {
        if (root == null) return root;

        if (key < root.key) root.left = delete(root.left, key);
        else if (key > root.key) root.right = delete(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;
                if (temp == null) return null;
                else root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        if (root == null) return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    private void updateArrayRepresentation() {
        arrayRepresentation.clear();
        fillArray(root);
    }

    private void fillArray(Node node) {
        if (node != null) {
            fillArray(node.left);
            arrayRepresentation.add(node.key);
            fillArray(node.right);
        }
    }

    public void displayArray() {
        System.out.println("1-D Array Representation:");
        for (int i = 0; i < arrayRepresentation.size(); i++) {
            System.out.println("Index " + i + ": " + arrayRepresentation.get(i));
        }
    }

    public void inOrder() {
        System.out.print("In-order: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public void preOrder() {
        System.out.print("Pre-order: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder() {
        System.out.print("Post-order: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAVL Tree Operations:");
            System.out.println("1. Insert (Multiple values supported: space or comma-separated)");
            System.out.println("2. Delete");
            System.out.println("3. Display 1-D Array");
            System.out.println("4. Display Traversals (Preorder, Inorder, Postorder)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter values to insert (space/comma-separated): ");
                    String[] values = scanner.nextLine().split("[ ,]+");
                    for (String value : values) {
                        tree.insert(Integer.parseInt(value));
                    }
                    break;
                case 2:
                    System.out.print("Enter value to delete: ");
                    int deleteValue = scanner.nextInt();
                    tree.delete(deleteValue);
                    break;
                case 3:
                    tree.displayArray();
                    break;
                case 4:
                    tree.preOrder();
                    tree.inOrder();
                    tree.postOrder();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
