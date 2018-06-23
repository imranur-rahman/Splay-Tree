public class _1305015_SplayTree {
    Node<Integer>root;

    Node<Integer> find(int data)
    {
        Node<Integer> now = root;
        while(true)
        {
            if(now == null)
                return null;

            if(data < now.data)
                now = now.left;
            else if(data > now.data)
                now = now.right;
            else if(now.data == data)
            {
                return now;
            }
        }
    }

    Node<Integer> findThisOrParent(int data)
    {
        if(root == null)
            return null;
        Node<Integer> now = root;
        while(true)
        {
            if(data < now.data  &&  now.left == null)
                return now;
            else if(data > now.data  &&  now.right == null)
                return now;

            if(data < now.data)
                now = now.left;
            else if(data > now.data)
                now = now.right;
            else if(now.data == data)
            {
                return now;
            }
        }
    }

    Node<Integer> search(int data)
    {
        Node<Integer> ret = find(data);
        if(ret != null)
            splay(ret);
        return ret;
    }

    Pair<Node<Integer>, Node<Integer>> split(Node<Integer> root, int data)
    {
        Node<Integer> left, right;
        Node<Integer> now = findThisOrParent(data);

        //System.out.print("found this or parent : ");
        //now.printNode();

        //BTreePrinter.printNode(root);
        splay(now);
        //System.out.println("splaying " + now.data);
        root = now;//very very important
        //BTreePrinter.printNode(root);
        //System.out.println("in split: now -> " + now.data);
        //System.out.println("is split: root: " + root.data);

        //now is currently the root.

        if(root.data <= data)
        {
            //System.out.println("root: " + root.data);
            //BTreePrinter.printNode(root);
            right = root.unlinkRightChild();
            left = root;
            //BTreePrinter.printNode(root);
            //System.out.println("root: " + root.data);
        }
        else
        {
            //System.out.println("root: " + root.data);
            //BTreePrinter.printNode(root);
            left = root.unlinkLeftChild();
            right = root;
            //BTreePrinter.printNode(root);
            //System.out.println("root: " + root.data);
        }
//        if(left != null)
//            System.out.println("in split left : " + left.data);
//        if(right != null)
//            System.out.println("in split right : " + right.data);
        return new Pair<>(left, right);
    }

    void insert(int data)
    {
        //System.out.println("insert start");
        if(root != null) {
            Pair<Node<Integer>, Node<Integer>> pair = split(root, data);
            root = new Node<>(data, pair.first, pair.second);
        }
        else
            root = new Node<>(data);
        //System.out.println("insert end");
    }

    Node<Integer> findMaxElement(Node<Integer> left)
    {
        if(left == null)
            return null;
        Node<Integer> now = left;
        while(true)
        {
            if(now.right == null)
                return now;
            now = now.right;
        }
    }

    void delete(int data)
    {
        //System.out.println("delete start");
//        BTreePrinter.printNode(root);
//        printTreeNodes(root);
        if(search(data) == null) //didn't find it
            return;
//        BTreePrinter.printNode(root);
//        printTreeNodes(root);

        //data is in the root, now we have to delete the root

        Node<Integer> left = root.unlinkLeftChild();
        Node<Integer> right = root.unlinkRightChild();

        //System.out.println("left child is: " + left.data);
        //System.out.println("right child is: " + right.data);

        Node<Integer> temp = findMaxElement(left);
        //System.out.println("max element is: " + temp.data);

        if(temp == null) // empty left tree
        {
            if(right == null)//will be an empty tree
            {
                root = null;
            }
            else
            {
                root = right;
                root.parent = null;
            }
            return;
        }

        root = left;
        splay(temp);

        root.makeRightChild(right);
        //System.out.println("delete end");
    }

    void printTreeNodes(Node<Integer> now)
    {
        if(now == null)
            return;
        printTreeNodes(now.left);
        now.printNode();
        printTreeNodes(now.right);
    }

    void splay(Node now)
    {
        if(root == null)
            return;

        //Node now = new Node(data);

        while(root != now)
        {
//            BTreePrinter.printNode(root);
//            printTreeNodes(root);
            Node parent = now.parent;
            if(parent.equals(root))
            {
                //root.printNode();
                //now.printNode();
                if(now.equals(parent.left))
                    rotateRight(now);
                else
                    rotateLeft(now);
                //root.printNode();
                //now.printNode();
                root = now;
                //root.printNode();
                //now.printNode();
            }
            else
            {
                Node grandParent = parent.parent;

                //zig zig to right
                if(now.equals(parent.right)  &&  parent.equals(grandParent.right))
                {
                    rotateLeft(parent);
                    rotateLeft(now);
                }
                //zig zig to left
                else if(now.equals(parent.left)  &&  parent.equals(grandParent.left))
                {
                    rotateRight(parent);
                    rotateRight(now);
                }
                //zig zag left right
                else if(now.equals(parent.right)  &&  parent.equals(grandParent.left))
                {
                    rotateLeft(now);
                    rotateRight(now);
                }
                else if(now.equals(parent.left)  &&  parent.equals(grandParent.right))
                {
                    rotateRight(now);
                    rotateLeft(now);
                }
                else
                {
                    System.out.println("error");
                }
                if(grandParent == root)
                    root = now;
            }
        }
    }

    private void rotateLeft(Node now) {
        Node parent = now.parent;
        Node grandParent = parent.parent;

        //System.out.println();
        //.out.print("now: ");
        //now.printNode();
        //System.out.print("parent: ");
        //parent.printNode();

        if(grandParent != null  &&  parent.equals(grandParent.left))
        {
            //System.out.println("grandparent : " + grandParent.data);
            grandParent.left = now;
            now.parent = grandParent;
        }
        else if(grandParent != null  &&  parent.equals(grandParent.right))
        {
            //System.out.println("grandparent : " + grandParent.data);
            grandParent.right = now;
            now.parent = grandParent;
        }

        now.parent = parent.parent;

//        parent.right = now.left;
//        if(parent.right != null)
//            parent.right.parent = parent;
        parent.makeRightChild(now.left);

//        now.left = parent;
//        parent.parent = now;
        now.makeLeftChild(parent);

//        System.out.println();
//        System.out.print("now: ");
//        now.printNode();
//        System.out.print("parent: ");
//        parent.printNode();
//
//        System.out.println("left rotated " + now.data);
    }

    private void rotateRight(Node now) {
        Node parent = now.parent;
        Node grandParent = parent.parent;

        if(grandParent != null  &&  parent.equals(grandParent.left))
        {
            grandParent.left = now;
            now.parent = grandParent;
        }
        else if(grandParent != null  &&  parent.equals(grandParent.right))
        {
            grandParent.right = now;
            now.parent = grandParent;
        }

        now.parent = parent.parent;

//        parent.left = now.right;
//        if(parent.left != null)
//            parent.left.parent = parent;
        parent.makeLeftChild(now.right);

//        now.right = parent;
//        parent.parent = now;
        now.makeRightChild(parent);


        //System.out.println("right rotated " + now.data);
    }
}
