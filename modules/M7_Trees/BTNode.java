/*
    Ellie Pike - pikeas01@pfw.edu
    Terrell Richey - richtj03@pfw.edu
    Patrick Rall - rallpw0pfw.edu
    Ethan Quispe - quiseh01@pfw.edu
    Seth Pfister - pfissg01@pfw.edu
*/

class BTNode<E> {
    private E data;
    private BTNode<E> left, right;

    public BTNode(E initialData, BTNode<E> initialLeft, BTNode<E> initialRight) {
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
    public void setLeft(BTNode<E> newLeft) {
        left = newLeft;
    }
    public BTNode<E> getLeft() {
        return left;
    }
    public void setRight(BTNode<E> newRight) {
        right = newRight;
    }
    public BTNode<E> getRight() {
        return right;
    }


    public static <E> long treeSize(BTNode<E> root) {
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
    private void buildSide(BTNode<E> branch1, StringBuilder sb, int depth, BTNode<E> branch2) {
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
    public BTNode<E> getLeftMost() {
        if(this.left == null) {
            return this;
        }

        return left.getLeftMost();
    }

    // The time complexity is O(n)
    public BTNode<E> getRightMost() {
        if(this.right == null) {
            return this;
        }

        return right.getRightMost();
    }


    public static void main(String[] args) {
        //
        BTNode<Integer> oneNode = new BTNode<>(1, null, null);
        BTNode<Integer> sevenNode = new BTNode<>(7, null, null);
        BTNode<Integer> nineNodeA = new BTNode<>(9, null, null);
        BTNode<Integer> twoNode = new BTNode<>(2, null, null);
        BTNode<Integer> sixNode = new BTNode<>(6, null, null);
        BTNode<Integer> nineNodeB = new BTNode<>(9, null, null);
        BTNode<Integer> fiveNodeA = new BTNode<>(5, null, null);
        BTNode<Integer> elevenNode = new BTNode<>(11, null, null);
        BTNode<Integer> fiveNodeB = new BTNode<>(5, null, null);

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
