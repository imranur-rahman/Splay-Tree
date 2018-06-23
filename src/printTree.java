import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class BTreePrinterTest {

    private static Node<Integer> test1() {
        Node<Integer> root = new Node<Integer>(2);
        Node<Integer> n11 = new Node<Integer>(7);
        Node<Integer> n12 = new Node<Integer>(5);
        Node<Integer> n21 = new Node<Integer>(2);
        Node<Integer> n22 = new Node<Integer>(6);
        Node<Integer> n23 = new Node<Integer>(3);
        Node<Integer> n24 = new Node<Integer>(6);
        Node<Integer> n31 = new Node<Integer>(5);
        Node<Integer> n32 = new Node<Integer>(8);
        Node<Integer> n33 = new Node<Integer>(4);
        Node<Integer> n34 = new Node<Integer>(5);
        Node<Integer> n35 = new Node<Integer>(8);
        Node<Integer> n36 = new Node<Integer>(4);
        Node<Integer> n37 = new Node<Integer>(5);
        Node<Integer> n38 = new Node<Integer>(8);

        root.left = n11;
        root.right = n12;

        n11.left = n21;
        n11.right = n22;
        n12.left = n23;
        n12.right = n24;

        n21.left = n31;
        n21.right = n32;
        n22.left = n33;
        n22.right = n34;
        n23.left = n35;
        n23.right = n36;
        n24.left = n37;
        n24.right = n38;

        return root;
    }

    private static Node<Integer> test2() {
        Node<Integer> root = new Node<Integer>(2);
        Node<Integer> n11 = new Node<Integer>(7);
        Node<Integer> n12 = new Node<Integer>(5);
        Node<Integer> n21 = new Node<Integer>(2);
        Node<Integer> n22 = new Node<Integer>(6);
        Node<Integer> n23 = new Node<Integer>(9);
        Node<Integer> n31 = new Node<Integer>(5);
        Node<Integer> n32 = new Node<Integer>(8);
        Node<Integer> n33 = new Node<Integer>(4);

        root.left = n11;
        root.right = n12;

        n11.left = n21;
        n11.right = n22;

        n12.right = n23;
        n22.left = n31;
        n22.right = n32;

        n23.left = n33;

        return root;
    }

    private static Node<Integer> test3() {
        Node<Integer> root = new Node<Integer>(4);
        Node<Integer> n11 = new Node<Integer>(2);
        Node<Integer> n12 = new Node<Integer>(6);
        Node<Integer> n21 = new Node<Integer>(1);
        Node<Integer> n22 = new Node<Integer>(3);
        Node<Integer> n23 = new Node<Integer>(5);
        Node<Integer> n24 = new Node<Integer>(7);
        Node<Integer> n31 = new Node<Integer>(8);
        Node<Integer> n32 = new Node<Integer>(9);
        Node<Integer> n33 = new Node<Integer>(1);
        Node<Integer> n34 = new Node<Integer>(2);
        Node<Integer> n35 = new Node<Integer>(3);
        Node<Integer> n36 = new Node<Integer>(4);
        Node<Integer> n37 = new Node<Integer>(5);
        Node<Integer> n38 = new Node<Integer>(6);

        root.makeLeftChild(n11);
        root.makeRightChild(n12);

        n11.makeLeftChild(n21);
        n11.makeRightChild(n22);
        n12.makeLeftChild(n23);
        n12.makeRightChild(n24);

//        n21.makeLeftChild(n31);
//        n21.makeRightChild(n32);
//        n22.makeLeftChild(n33);
//        n22.makeRightChild(n34);
//        n23.makeLeftChild(n35);
//        n23.makeRightChild(n36);
//        n24.makeLeftChild(n37);
//        n24.makeRightChild(n38);

        return root;
    }

    public static void main(String[] args) {

        //BTreePrinter.printNode(test1());
        //BTreePrinter.printNode(test2());
//
//        Node root = test3();
//        BTreePrinter.printNode(root);
//        SplayTree splayTree = new SplayTree();
//        splayTree.root = root;




        /*for(int i = 1; i <= 8; ++i)
        {
            System.out.println("searching for " + i);
            Node now = splayTree.search(i);
            BTreePrinter.printNode(splayTree.root);
        }*/

        //now.printNode();

//        Node now = splayTree.search(6);
//        //splayTree.splay(now);
//        BTreePrinter.printNode(splayTree.root);

//        splayTree.delete(6);
//        BTreePrinter.printNode(splayTree.root);
//
//        splayTree.insert(6);
//        BTreePrinter.printNode(splayTree.root);

        _1305015_SplayTree splayTree = new _1305015_SplayTree();
        for(int i = 0; i < 5; ++i)
        {
            Random random = new Random();
            splayTree.insert(random.nextInt(10));
            System.out.println("printing tree");
            BTreePrinter.printNode(splayTree.root);
        }

    }
}

class Node<T extends Comparable<?>> {
    Node<T> left, right;
    Node<T> parent;
    T data;
    int height;

    public Node() {}

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent, Node<T> left, Node<T> right)
    {
        this.data = data;
        this.parent = parent;
        makeLeftChild(left);
        makeRightChild(right);
    }

    public Node(T data, Node<T> left, Node<T> right)
    {
        this.data = data;
        makeLeftChild(left);
        makeRightChild(right);
    }

    public void makeLeftChild(Node<T> node)
    {
        this.left = node;
        if(node != null)
            node.parent = this;
    }

    public void makeRightChild(Node<T> node)
    {
        this.right = node;
        if(node != null)
            node.parent = this;
    }

    public Node<T> unlinkLeftChild()
    {
        if(this.left != null)
            this.left.parent = null;
        Node<T> temp = this.left;
        this.left = null;
        return temp;
    }

    public Node<T> unlinkRightChild()
    {
        if(this.right != null)
            this.right.parent = null;
        Node<T> temp = this.right;
        this.right = null;
        return temp;
    }

    public void printNode()
    {
        System.out.print(data);
        if(left != null)
            System.out.print(", left: " + left.data);
        if(right != null)
            System.out.print(" right: " + right.data);
        if(parent != null)
            System.out.print(", parent: " + parent.data);
        System.out.println();
    }
}

class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(Node<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}