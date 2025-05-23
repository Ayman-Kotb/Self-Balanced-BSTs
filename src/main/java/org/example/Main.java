package org.example;


import org.example.BSTs.AVLTree;
import org.example.BSTs.BSTOperations;
import org.example.BSTs.BatchOperations;
import org.example.BSTs.RedBlackTree;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BSTOperations BST;

        System.out.println(CYAN+"👋 Welcome to your Friendly Dictionary CLI!"+RESET);
        System.out.println(CYAN+"Choose a BST implementation:"+RESET);
        System.out.println("1. AVL Tree");
        System.out.println("2. Red Black Tree");
        System.out.print(BLUE+"Enter your choice (1 or 2): "+RESET);

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            BST = new AVLTree<String>();
            System.out.println("✅ AVL Tree selected.");
        } else {
            BST = new RedBlackTree<String>();
            System.out.println("✅ Red Black Tree selected.");
        }

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println(GREEN + "1. Insert a word" + RESET);
            System.out.println(YELLOW + "2. Delete a word" + RESET);
            System.out.println(PURPLE + "3. Search for a word" + RESET);
            System.out.println(BLUE + "4. Batch insert from file" + RESET);
            System.out.println(CYAN + "5. Batch delete from file" + RESET);
            System.out.println(WHITE + "6. Get the size of the dictionary" + RESET);
            System.out.println(PURPLE + "7. Get the height of the Tree" + RESET);
            System.out.println(BLUE + "8. Visualize the Tree" + RESET);
            System.out.println(RED + "9. Exit" + RESET);
            System.out.print("Enter your choice: ");

            int op = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (op) {
                case 1 -> {
                    System.out.print("Enter word to insert: ");
                    String word = scanner.nextLine().trim();
                    if (BST.insert(word)) {
                        System.out.println(GREEN + "✅ Word inserted successfully." + RESET);
                    } else {
                        System.out.println(RED + "❌ Word already exists." + RESET);
                    }
                }
                case 2 -> {
                    System.out.print("Enter word to delete: ");
                    String word = scanner.nextLine().trim();
                    if (BST.delete(word)) {
                        System.out.println(GREEN+"✅ Word deleted successfully."+RESET);
                    } else {
                        System.out.println(RED+"❌ Word doesn't exist in the dictionary."+RESET);
                    }
                }
                case 3 -> {
                    System.out.print("Enter word to search: ");
                    String word = scanner.nextLine().trim();
                    if (BST.search(word)) {
                        System.out.println(GREEN+"✅ Word found in the dictionary."+RESET);
                    } else {
                        System.out.println(RED+"❌ Word not found in the dictionary."+RESET);
                    }
                }
                case 4 -> {
                    System.out.print("Enter path to batch insert file: ");
                    String path = scanner.nextLine().trim();
                    BatchOperations.batchInsert(BST, path);
                }
                case 5 -> {
                    System.out.print("Enter path to batch delete file: ");
                    String path = scanner.nextLine().trim();
                    BatchOperations.batchDelete(BST, path);
                }
                case 6 -> {
                    System.out.println("📦 Dictionary size: " + BST.size());
                }
                case 7 -> {
                    System.out.println("🌳 Tree height: " + BST.height());
                }
                case 8 -> {
                    JFrame frame = new JFrame("Tree Visualization");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.add(BST.draw());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                case 9 -> {

                    System.out.println(YELLOW+"👋 Thanks for using your Friendly Dictionary!"+RESET);
                    return;
                }
                default -> System.out.println(RED+"❌ Invalid option. Please try again."+RESET);
            }
        }
    }




}