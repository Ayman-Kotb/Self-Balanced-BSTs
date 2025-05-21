//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import BSTs.RedBlackTree;

public class Main {
    public static void main(String[] args) {
        System.out.println("NO main");
        RedBlackTree<Integer> riri = new RedBlackTree<>();
        System.out.println("hamada");
        for (int i = 1; i <= 15; i++) {
            riri.insert(i);
        }
        riri.insert(50);
        riri.insert(60);
        riri.insert(61);
        riri.insert(62);
        riri.insert(16);

        System.out.print(riri.height()+"\n");
        System.out.print(riri.size());

        riri.printPrettyTree();
        riri.delete(50);
        riri.delete(13);
        riri.delete(60);
        riri.delete(100);
        riri.delete(14);
        riri.delete(10);
        riri.delete(14);
        riri.delete(10);

        riri.printPrettyTree();
        System.out.print(riri.height()+"\n");
        System.out.print(riri.size());
        System.out.print(riri.search(14));
        System.out.print(riri.search(16));











    }
}