import java.util.Random;
import java.util.Scanner;

public class main {

    static int N = 104;//number of operations
    static int operationType[] = new int[N];
    static int operationOnNode[] = new int[N];
    static double executionTimeSplayTree[] = new double[N];
    static double executionTimeAVLTree[] = new double[N];
    static _1305015_SplayTree splayTree;
    static AVLTree avlTree;
    static long startTime, endTime, elapsedNanoSeconds;
    static double elapsedSeconds;

    public static void splay_tree()
    {
        splayTree = new _1305015_SplayTree();

        for(int i = 0; i < N; ++i)
        {
            if(operationType[i] == 0)//search
            {
                timeStart();
                Node<Integer> temp = splayTree.search(operationOnNode[i]);
                timeEndAndPrint(i, executionTimeSplayTree);
                if(temp == null)
                    System.out.println(operationOnNode[i] + " not found");
                else
                    System.out.println(operationOnNode[i] + " found");
            }
            else if(operationType[i] == 1)//insert
            {
                timeStart();
                splayTree.insert(operationOnNode[i]);
                timeEndAndPrint(i, executionTimeSplayTree);
                System.out.println("inserting " + operationOnNode[i]);
            }
            else if(operationType[i] == 2)//delete
            {
                timeStart();
                splayTree.delete(operationOnNode[i]);
                timeEndAndPrint(i, executionTimeSplayTree);
                System.out.println("deleting " + operationOnNode[i]);
            }
            //BTreePrinter.printNode(splayTree.root);
        }
    }

    private static void avl_tree() {
        avlTree = new AVLTree();

        for(int i = 0; i < N; ++i)
        {
            if(operationType[i] == 0)//search
            {
                timeStart();
                boolean found = avlTree.search(operationOnNode[i]);
                timeEndAndPrint(i, executionTimeAVLTree);
                if(found)
                    System.out.println(operationOnNode[i] + " not found");
                else
                    System.out.println(operationOnNode[i] + " found");
            }
            else if(operationType[i] == 1)//insert
            {
                timeStart();
                avlTree.insert(operationOnNode[i]);
                timeEndAndPrint(i, executionTimeAVLTree);
                System.out.println("inserting " + operationOnNode[i]);
            }
            else if(operationType[i] == 2)//delete
            {
                timeStart();
                avlTree.remove(operationOnNode[i]);
                timeEndAndPrint(i, executionTimeAVLTree);
                System.out.println("deleting " + operationOnNode[i]);
            }
            BTreePrinter.printNode(avlTree.root);
        }
    }

    public static void console()
    {
        splayTree = new _1305015_SplayTree();
        avlTree = new _1305015_AVL_Tree();

        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            //System.out.println("0. Search\n1. Insert\n2. Delete");
            int n = scanner.nextInt();
            if(n == 0)
            {
                int temp = scanner.nextInt();
                avlTree.find(temp);
                //splayTree.find(temp);
            }
            else if(n == 1)
            {
                int temp = scanner.nextInt();
                avlTree.insert(temp);
                //splayTree.insert(temp);
            }
            else if(n == 2)
            {
                int temp = scanner.nextInt();
                avlTree.remove(temp);
                //splayTree.delete(temp);
            }
            else
            {
                break;
            }
            BTreePrinter.printNode(avlTree.root);
            //BTreePrinter.printNode(splayTree.root);
        }
    }


    public static void main(String[] args) {
        //BTreePrinterTest.main(args);
        generateOperations();
        //splay_tree();
        //avl_tree();
        console();
    }

    private static void timeStart() {
        startTime = System.nanoTime();
    }

    public static void timeEndAndPrint(int i, double[] time)
    {
        endTime = System.nanoTime();
        elapsedNanoSeconds = endTime - startTime;
        elapsedSeconds = elapsedNanoSeconds / 1000000000.0;

        time[i] = elapsedSeconds;

        //System.out.println(elapsedSeconds);
        System.out.printf("%.9f", elapsedSeconds);
        System.out.println();
    }

    private static void generateOperations() {
        Random random = new Random();
        for(int i = 0; i < N; ++i)
        {
            operationType[i] = random.nextInt(3);
            operationOnNode[i] = random.nextInt((N - 0) + 1) + 0; //((max - min) + 1) + min
        }
    }
}
