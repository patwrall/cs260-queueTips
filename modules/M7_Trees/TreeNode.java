package M7_Trees;

/*
    Ellie Pike - pikeas01@pfw.edu
    Terrell Richey - richtj03@pfw.edu
    Patrick Rall - rallpw0pfw.edu
    Ethan Quispe - quiseh01@pfw.edu
    Seth Pfister - pfissg01@pfw.edu
*/

class TreeNode<E>  {
    private E data;
    private TreeNode<E> left, right;

    public TreeNode(E initialData, TreeNode<E> initialLeft, TreeNode<E> initialRight) {
        data = initialData;
        left = initialLeft;
        right = initialRight;
    }

    public void setData(E newData) {
        data = newData;
    }
    public E getData() {
        return data;
    }
    public void setLeft(TreeNode<E> newLeft) {
        left = newLeft;
    }
    public TreeNode<E> getLeft() {
        return left;
    }
    public void setRight(TreeNode<E> newRight) {
        right = newRight;
    }
    public TreeNode<E> getRight() {
        return right;
    }


    public static <E> long treeSize(TreeNode<E> root) {
        if (root == null)
            return 0;
        else
            return 1 + treeSize(root.left) + treeSize(root.right);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tree:\n");
        buildString(sb, 0);
        return sb.toString();
    }
    private void buildString(StringBuilder sb, int depth) {
        for (int i = 1; i < depth; i++) {
            sb.append("    ");
        }
        if (depth > 0) {
            sb.append("\\___");
        }
        sb.append(data).append("\n");
        buildSide(right, sb, depth, left);
        buildSide(left, sb, depth, right);
    }
    private void buildSide(TreeNode<E> branch1, StringBuilder sb, int depth, TreeNode<E> branch2) {
        if (branch1 != null) {
            branch1.buildString(sb, depth + 1);
        } else if (branch2 != null) {
            for (int i = 1; i <= depth + 1; i++) {
                sb.append("    ");
            }
            sb.append("--\n");
        }
    }
    // The time complexity is O(n)
    public TreeNode<E> getLeftMost() {
        if(this.left == null) {
            return this;
        }

        return left.getLeftMost();
    }

    // The time complexity is O(n)
    public TreeNode<E> getRightMost() {
        if(this.right == null) {
            return this;
        }

        return right.getRightMost();
    }

    public int getChildCount() {
        int count = 0;

        if(left != null) {
            count++;
        }

        if(right != null) {
            count++;
        }

        return count;
    }


    public static void main(String[] args) {
        //
        TreeNode<Integer> oneNode = new TreeNode<>(1, null, null);
        TreeNode<Integer> sevenNode = new TreeNode<>(7, null, null);
        TreeNode<Integer> nineNodeA = new TreeNode<>(9, null, null);
        TreeNode<Integer> twoNode = new TreeNode<>(2, null, null);
        TreeNode<Integer> sixNode = new TreeNode<>(6, null, null);
        TreeNode<Integer> nineNodeB = new TreeNode<>(9, null, null);
        TreeNode<Integer> fiveNodeA = new TreeNode<>(5, null, null);
        TreeNode<Integer> elevenNode = new TreeNode<>(11, null, null);
        TreeNode<Integer> fiveNodeB = new TreeNode<>(5, null, null);

        //Connect Nodes
        /*
            1
           / \
          7	  9A
        */
        oneNode.setLeft(sevenNode);
        oneNode.setRight(nineNodeA);
        /*
            7
           / \
          2	  6
        */
        sevenNode.setLeft(twoNode);
        sevenNode.setRight(sixNode);
        /*
            6
           / \
          5A  11
        */
        sixNode.setLeft(fiveNodeA);
        sixNode.setRight(elevenNode);
        /*
            9A
              \
          	   9B
        */
        nineNodeA.setLeft(null);
        nineNodeA.setRight(nineNodeB);
        /*
            9B
              \
          	   5B
        */
        nineNodeB.setLeft(fiveNodeB);
        nineNodeB.setRight(null);


        //Printout
        System.out.println(oneNode);
        System.out.println("Number of Nodes: " + treeSize(oneNode));

        //Part C
        //i 2

        //ii
        System.out.println("Left Most Node: " + oneNode.getLeftMost());
        System.out.println("Right Most Node: " + oneNode.getRightMost());

        //iii
        //a
        oneNode.getLeftMost().setData(44);
        System.out.println("Left Most Node: " + oneNode.getLeftMost());

        //b
        System.out.println("Right Most Node: " + oneNode.getRightMost());

        //c
        System.out.println("Left most node of the right most node: " + oneNode.getRightMost().getLeftMost());

        //iv
        // The time complexity of getLeftMost() is O(n) because the function is called once recursively until reaching a condition.

        // n = number of nodes
        // The time complexity of treeSize is O(n) because number of operations is linear because it grows linearly with the input size.


    }

}
