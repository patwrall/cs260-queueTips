package M7_Trees;

public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;
    public BinarySearchTree() {
        root = null;
        size = 0;
    }


    public void add(E data) {
        root = add(root, data);
        size++;
    }

    // Helper method for add recursion
    // Complete the add() method that adds a new data element to the BST.
    // A node <= parent will go to the left subtree, and a node > parent will go to the right subtree.
    private TreeNode<E> add(TreeNode<E> node, E data) {

        TreeNode<E> ret;
        if(root == null && node == root) {
            node = new TreeNode<>(data, null, null);
            return node;
        }


        if (data.compareTo(node.getData()) <= 0) {
            if (node.getLeft() == null) {
                ret = new TreeNode<>(data, null, null);
                node.setLeft(ret);
            } else {
                add(node.getLeft(),data);
            }
        } else {
            if(node.getRight() == null){
                ret = new TreeNode<>(data, null, null);
                node.setRight(ret);
            } else {
                add(node.getRight(), data);
            }
        }
        return root;
    }

    public E remove(E data) {
        TreeNode<E> removedNode = remove(root, data);
        if (removedNode != null) {
            size--;
            return removedNode.getData();
        }

        return null; // Data not found
    }

    // Helper method for remove recursion
    private TreeNode<E> remove(TreeNode<E> node, E data) {
        if (node == null)
            return null; // Base case: Data not found
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(node.getLeft(), data)); // Traverse left
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(node.getRight(), data)); // Traverse right
        } else {
            // Found the node to be deleted
            int children = node.getChildCount();

            // Node with no child
            if(children == 0) {
                return null;
            }

            // Node with only one child
            if(children == 1) {
                if(node.getLeft() == null) {
                    return node.getRight();
                } else {
                    return node.getLeft();
                }
            }

            // Node with two children:
            // Get the inorder successor (smallest node greater than this node)
            TreeNode<E> successor = node.getRight().getLeftMost();

            // Copy the inorder successor's content to this node
            node.setData(successor.getData());

            // Delete the inorder successor
            node.setRight(remove(node.getRight(), successor.getData()));
        }
        return node;
    }

    public String toString(){
        return root.toString();
    }

    public int size() {
        return size;
    }

    public void printInOrder () {
        System.out.println("In-order traversal: ");
        printInOrder(root);
        System.out.println();

    }

    // Helper method for printInOrder recursion
    private void printInOrder(TreeNode<E> node) {
        if (node != null) {
            //Recurse left
            printInOrder(node.getLeft());
            //Print node data
            System.out.println(node.getData());
            //Recurse right
            printInOrder(node.getRight());
        }
    }

    public boolean contains(E data) {
        return contains(root, data);
    }
    // Helper method for contains recursion
    private boolean contains(TreeNode<E> node, E data) {
        if (node == null)
            return false;
        if (data.compareTo(node.getData()) == 0)
            return true;
        else if (data.compareTo(node.getData()) < 0)
            return contains(node.getLeft(), data);
        else
            return contains(node.getRight(), data);
    }



    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        bst.add(40);

        System.out.println(bst);

        bst.printInOrder();

        System.out.println("Tree contains " + bst.size() + " nodes.");
        System.out.println("Tree contains 50: " + bst.contains(50));  // Expected output: true
        System.out.println("Tree contains 25: " + bst.contains(25));  // Expected output: false

        bst.add(25);
        System.out.println("After adding 25:\n" + bst);

        bst.remove(50);
        System.out.println("After removing 50:\n" + bst);

        bst.remove(40);
        System.out.println("After removing 40:\n" + bst);

        bst.remove(20);
        System.out.println("After removing 20:\n" + bst);
    }
}