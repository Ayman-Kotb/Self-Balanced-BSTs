package BSTs;

public interface BSTOperations<T extends Comparable<T>> {
   boolean insert(T key);
   boolean delete(T key);
   boolean search(T key);
   int size();
   int Height();
}
