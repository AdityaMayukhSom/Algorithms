import java.util.*;
import java.io.*;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
};

class Solution {
    /**
     * Variable to hold the diameter of the binary tree
     */
    private int diam;

    /**
     * Calculates the height of a binary tree.
     *
     * @param node the root node of the binary tree
     * @return the height of the binary tree
     */
    public int height(Node node) {
        // Base case: if the node is null, the height is 0
        if (node == null) {
            return 0;
        }

        // Recursively calculate the height of the left and right subtrees
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // Return the maximum height between the left and right subtrees, plus 1 for the
        // current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Calculates the diameter of a binary tree passing through the given root node.
     * The diameter of a binary tree is defined as the length of the longest path
     * between any two nodes.
     *
     * <p>
     * Note: The value of {@code diam} can be modified after calling this
     * method.
     * </p>
     * 
     * @param root the root node of the binary tree
     * @return the diameter of the binary tree passing through the specified root
     *         node
     */
    public int calculateDiameter(Node root) {
        if (root == null) {
            return 0;
        }

        // Calculate the height of the left subtree
        int leftHeight = calculateDiameter(root.left);

        // Calculate the height of the right subtree
        int rightHeight = calculateDiameter(root.right);

        // Calculate the probable diameter passing through the current root node
        int probableDiameter = leftHeight + rightHeight + 1;

        // Update the maximum diameter if the probable diameter is greater
        if (this.diam < probableDiameter) {
            this.diam = probableDiameter;
        }

        // Return the height of the current subtree (maximum height between left and
        // right subtrees) plus one
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Calculates the diameter of a binary tree rooted at the given node.
     * 
     * <p>
     * The diameter of a binary tree is defined as the longest path between any two
     * nodes in the tree. It represents the maximum number of nodes that can be
     * encountered in any path in the tree.
     * </p>
     * 
     * <p>
     * The diameter is calculated by traversing the binary tree recursively. The
     * method modifies the class variable {@code diam} to store the calculated
     * diameter. It is important to note that the value of {@code diam} is not
     * guaranteed to remain the same before and after calling this method.
     * </p>
     * 
     * 
     * @param root the root node of the binary tree
     * @return the diameter of the binary tree
     */
    public int diameter(Node root) {
        // Initialize the value of diameter as zero since this is the minimum possible
        // diameter
        this.diam = 0;

        // Calculate the diameter of the binary tree rooted at the given node
        calculateDiameter(root);

        // Return the calculated diameter
        return this.diam;
    }

    public static ArrayList<Integer> zigzagTreeTraversal(TreeNode<Integer> root) {
        ArrayList<Integer> ans = new ArrayList<Integer>();

        if (root == null)
            return ans;

        Queue<TreeNode<Integer>> q = new ArrayDeque<TreeNode<Integer>>();

        boolean leftToRight = true;

        q.add(root);
        q.add(null);

        ArrayList<TreeNode<Integer>> temp = new ArrayList<TreeNode<Integer>>();
        while (!q.isEmpty()) {

            TreeNode<Integer> el = q.remove();

            if (el == null) {
                if (leftToRight) {
                    for (int i = 0; i < temp.size(); i++) {
                        ans.add(temp.get(i).data);
                    }
                } else {
                    for (int i = temp.size() - 1; i >= 0; i--) {
                        ans.add(temp.get(i).data);
                    }
                }

                temp.clear();
                leftToRight = !leftToRight;
                if (!q.isEmpty()) {
                    q.add(null);
                }
            } else {
                temp.add(el);

                if (el.left != null) {
                    q.add(el.left);
                }

                if (el.right != null) {
                    q.add(el.right);
                }
            }
        }

        return ans;
    }

}
