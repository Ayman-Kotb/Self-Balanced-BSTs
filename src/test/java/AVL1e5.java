import org.example.BSTs.AVLTree;
import org.example.BSTs.BSTOperations;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AVL1e5 {
    int countN = 100000;
    @Test
    public void testBasicOperations() {
        BSTOperations<String> tree = new AVLTree<>();

        assertTrue(tree.insert("apple"));
        assertTrue(tree.insert("banana"));
        assertTrue(tree.search("apple"));
        assertTrue(tree.delete("apple"));
        assertFalse(tree.search("apple"));
        assertFalse(tree.delete("apple"));
        System.out.println(tree.size());
        System.out.println(tree.height());
    }

    @Test
    public void testDeleteAll() {
        BSTOperations<String> tree = new AVLTree<>();
        int count = countN;
        for (int i = 0; i < count; i++) {
            assertTrue(tree.insert("word" + i));
        }

        for (int i = 0; i < count; i++) {
            assertTrue(tree.search("word" + i));
        }

        for (int i = 0; i < count; i++) {
            assertTrue(tree.delete("word" + i));
        }

        for (int i = 0; i < count; i++) {
            assertFalse(tree.search("word" + i));
        }

        System.out.println("Finished inserting and verifying " + count + " words.");
        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Height: " + tree.height());


    }
    @Test
    public void testRandomInsertions() {
        BSTOperations<String> tree = new AVLTree<>();
        int count = countN;
        Random random = new Random();
        HashSet<String> inserted = new HashSet<>();

        for (int i = 0; i < count; i++) {
            String word = generateRandomWord(random, 5);
            while (!inserted.add(word)) {
                word = generateRandomWord(random, 5);
            }
            assertTrue(tree.insert(word));
        }

        for (String word : inserted) {
            assertTrue(tree.search(word));
        }

        System.out.println("Finished inserting and verifying " + count + " random words.");
        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Height: " + tree.height());
    }

    @Test
    public void testWordSize10() {
        BSTOperations<String> tree = new AVLTree<>();
        int count = countN;
        List<String> words = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String word = generateRandomWord(new Random(), 10);
            assertTrue(tree.insert(word));
            words.add(word);
        }

        for (String word : words) {
            assertTrue(tree.search(word));
        }

        System.out.println("Finished inserting and verifying " + count + " random words of size 10.");
        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Height: " + tree.height());
    }
    private String generateRandomWord(Random random, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
    @Test
    public void testWordSize15() {
        BSTOperations<String> tree = new AVLTree<>();
        int count = countN;
        List<String> words = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String word = generateRandomWord(new Random(), 15);
            assertTrue(tree.insert(word));
            words.add(word);
        }

        for (String word : words) {
            assertTrue(tree.search(word));
        }

        System.out.println("Finished inserting and verifying " + count + " random words of size 15.");
        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Height: " + tree.height());
    }

    @Test
    public void testWordSizeRandom1to20() {
        BSTOperations<String> tree = new AVLTree<>();
        int count = countN;
        Random random = new Random();
        HashSet<String> inserted = new HashSet<>();

        for (int i = 0; i < count; i++) {
            int wordSize = random.nextInt(20) + 5;
            String word = generateRandomWord(random, wordSize);
            while (!inserted.add(word)) {
                word = generateRandomWord(random, wordSize);
            }
            assertTrue(tree.insert(word));
        }

        for (String word : inserted) {
            assertTrue(tree.search(word));
        }

        System.out.println("Finished inserting and verifying " + count + " random words of random sizes (1 to 20).");
        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Height: " + tree.height());
    }



}
