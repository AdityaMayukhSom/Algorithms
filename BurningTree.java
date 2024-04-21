import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BurningTree {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private static Node targetNode;

    private static class Container {
        Node node;
        int time;

        Container(Node node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    private static void createMapping(Node root, Map<Node, Node> nodeToParent, int target) {
        if (root.data == target) {
            targetNode = root;
        }

        if (root.left != null) {
            nodeToParent.put(root.left, root);
            createMapping(root.left, nodeToParent, target);
        }

        if (root.right != null) {
            nodeToParent.put(root.right, root);
            createMapping(root.right, nodeToParent, target);
        }
    }

    public static int minTime(Node root, int target) {
        targetNode = null;
        int minTime = 0;
        Map<Node, Node> nodeToParent = new HashMap<>();
        Set<Node> vis = new HashSet<>();
        nodeToParent.put(root, null);
        createMapping(root, nodeToParent, target);
        Queue<Container> queue = new LinkedList<>();
        queue.offer(new Container(targetNode, 0));
        vis.add(targetNode);
        while (!queue.isEmpty()) {
            Container ctr = queue.poll();
            minTime = ctr.time;

            if (ctr.node.left != null && !vis.contains(ctr.node.left)) {
                queue.offer(new Container(ctr.node.left, ctr.time + 1));
                vis.add(ctr.node.left);
            }
            if (ctr.node.right != null && !vis.contains(ctr.node.right)) {
                queue.offer(new Container(ctr.node.right, ctr.time + 1));
                vis.add(ctr.node.right);
            }

            Node parent = nodeToParent.get(ctr.node);
            if (parent != null && !vis.contains(parent)) {
                queue.offer(new Container(parent, ctr.time + 1));
                vis.add(parent);
            }
        }
        return minTime;
    }

    static Node buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    static void printInorder(Node root) {
        if (root == null)
            return;

        printInorder(root.left);
        System.out.print(root.data + " ");

        printInorder(root.right);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            String s = br.readLine();
            int target = Integer.parseInt(br.readLine());
            Node root = buildTree(s);

            System.out.println(BurningTree.minTime(root, target));
            t--;
        }
        br.close();
        fis.close();
    }
}
