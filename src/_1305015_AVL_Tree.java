public class _1305015_AVL_Tree {
    boolean atRoot;
    Node errorNode;

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



    /*// This method mainly calls insertRec()
    void normalInsert(int key) {
        root = insertRec(root, key);
    }

    *//* A recursive function to insert a new key in BST *//*
    Node<Integer> insertRec(Node<Integer> root, int key) {

        *//* If the tree is empty, return a new node *//*
        if (root == null) {
            root = new Node(key);
            return root;
        }

        *//* Otherwise, recur down the tree *//*
        if (key < root.data)
            root.left = insertRec(root.left, key);
        else if (key > root.data)
            root.right = insertRec(root.right, key);

        *//* return the (unchanged) node pointer *//*
        return root;
    }

    public Node<Integer> insert(int key) {
        // Insert node normally
        if(find(key) != null){
            return null;
        }

        else{
            normalInsert(key);
            Node<Integer> newInsert = find(key);

            // Check balance
            if(isBalanced(root)){
                // If it is balanced then it needs no shifting.
                // Return Node and end method
                return newInsert;
            }

            // If not balanced, then shifting is needed
            else{
                balance(findErrorNode(root));

            }

            //Next perform a rotation on the node that is unbalanced
            //Figure what type of rotation is needed first
            return newInsert;
        }
    }

    public boolean violatesAVL(Node n){
        if(n == null){
            return false;
        }
        else if((Math.abs(get_height(n.left) - get_height(n.right)) < 2)){
            return false;
        }
        else{
            return true;
        }
    }

    public void rightRotation(Node n) {
        if(n.parent == null){
            atRoot = true;
        }

        System.out.println("right rotation");

        Node temp = n.left;
        n.left = temp.right;
        temp.right = n;
        temp.parent = n.parent;
        n.parent = temp;

        if(!atRoot){
            temp.parent.right = temp;

        }
        if(atRoot){
            root = temp;
            root.parent = null;
        }
        if(n.left != null){
            n.left.parent = n;
        }


        updateHeight(n);
        updateHeight(temp);

        if(!atRoot){
            updateRestofTree(temp.parent);
        }
    }

    public void leftRotation(Node n){
        if(n.parent == null){
            atRoot = true;
        }

        System.out.println("left rotation");

        Node temp = n.right;
        n.right = temp.left;
        temp.left = n;
        temp.parent = n.parent;
        n.parent = temp;

        if(!atRoot){
            temp.parent.left = temp;

        }
        if(atRoot){
            root = temp;
            root.parent = null;
        }
        if(n.right != null){
            n.right.parent = n;
        }


        updateHeight(n);
        updateHeight(temp);

        if(!atRoot){
            updateRestofTree(temp.parent);
        }

    }

    public void doubleRightRotation(Node n){
        Node temp = n;
        leftRotation(n.left);
        if(!isBalanced(root)) {
            rightRotation(n);
        }
    }

    public void doubleLeftRotation(Node n){
        Node temp = n;
        rightRotation(n.right);
        if(!isBalanced(root)) {
            leftRotation(n);
        }

    }

    public boolean isBalanced(Node n){
        if(n == null){
            return true;
        }
        else if(!(Math.abs(get_height(n.left) - get_height(n.right)) < 2)){
            return false;
        }
        else{
            return isBalanced(n.left) & isBalanced(n.right);
        }
    }

    public Node<Integer> findErrorNode(Node n){
        if(isBalanced(n)){
            return n.parent;
        }
        else{
            if(get_height(n.left) > get_height(n.right)){
                return findErrorNode(n.left);
            }
            else{
                return findErrorNode(n.right);
            }
        }
    }

    // This method mainly calls deleteRec()
    void normalDelete(int key)
    {
        root = deleteRec(root, key);
    }

    *//* A recursive function to insert a new key in BST *//*
    Node deleteRec(Node<Integer> root, int key)
    {
        *//* Base Case: If the tree is empty *//*
        if (root == null)  return root;

        *//* Otherwise, recur down the tree *//*
        if (key < root.data)
            root.left = deleteRec(root.left, key);
        else if (key > root.data)
            root.right = deleteRec(root.right, key);

            // if key is same as root's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    public int minValue(Node<Integer> node)
    {
        int ret = node.data;
        while(node.left != null)
        {
            ret = node.left.data;
            node = node.left;
        }
        return ret;
    }

    public void remove(int key) {
        // Insert node normally
        if(find(key) != null){
            normalDelete(key);

            // Check balance
            if (!isBalanced(root)) {
                balance(findErrorNode(root));
            }
        }
    }

    public void balance(Node<Integer> n){
        if(get_height(n.left) > get_height(n.right)){
            //check if double rotation needed
            if(get_height(n.left.right) > get_height(n.left.left)){
                doubleRightRotation(n);
            }
            else{
                rightRotation(n);
            }
        }

        // RIGHT > left
        else if(get_height(n.right) > get_height(n.left)){
            //check if double rotation needed
            if(get_height(n.right.left) > get_height(n.right.right)){
                doubleLeftRotation(n);

            }
            else{
                leftRotation(n);
            }
        }
    }

    public int get_height(Node<Integer> node)
    {
        //updateHeight(node);
        if(node != null)
            return node.height;
        else
            return -1;
    }

    public void updateRestofTree(Node<Integer> n){
        updateHeight(n);
        if(n.parent!=null){
            updateRestofTree(n.parent);
        }
    }

    private static final void updateHeight(Node<Integer> node) {
        int leftHeight = (node.left == null) ? -1 : (node.left).height;
        int rightHeight = (node.right == null) ? -1 : (node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }*/
}
