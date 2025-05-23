package org.example.BSTs;

import javax.swing.*;

public interface BSTOperations<T extends Comparable<T>> {
   boolean insert(T key);
   boolean delete(T key);
   boolean search(T key);
   int size();
   int height();
   JPanel draw();
}
