//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import BSTs.RedBlackTree;

public class Main {
    public static void main(String[] args) {
        System.out.println("NO main");
        RedBlackTree<Integer> riri = new RedBlackTree<>();
        riri.insert(5);
        riri.insert(6);
        riri.insert(7);
        riri.insert(4);
        riri.insert(1);
        riri.insert(10);
        riri.insert(8);

        riri.printPrettyTree();

    }
}