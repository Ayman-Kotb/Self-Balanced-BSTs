package org.example.BSTs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BatchOperations {

    public static void batchInsert(BSTOperations BST, String filePath) {
        int added = 0, alreadyExists = 0;
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();
                if (BST.insert(word)) added++;
                else alreadyExists++;
            }
            System.out.println("✅ Batch insert completed.");
            System.out.println("Newly added: " + added);
            System.out.println("Already existing: " + alreadyExists);
        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found: " + filePath);
        }
    }

    public static void batchDelete(BSTOperations BST, String filePath) {
        int deleted = 0, notFound = 0;
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();
                if (BST.delete(word)) deleted++;
                else notFound++;
            }
            System.out.println("✅ Batch delete completed.");
            System.out.println("Deleted: " + deleted);
            System.out.println("Non-existing: " + notFound);
        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found: " + filePath);
        }
    }
}