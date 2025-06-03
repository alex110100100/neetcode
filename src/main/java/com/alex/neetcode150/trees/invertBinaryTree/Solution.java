package com.alex.neetcode150.trees.invertBinaryTree;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class RecursiveSolution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

class IterativeSolutionBFS {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            TreeNode curr = q.poll();

            TreeNode temp;
            temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if (curr.left != null)
                q.offer(curr.left);

            if (curr.right != null)
                q.offer(curr.right);
        }

        return root;
    }
}

class IterativeSolutionDFS {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            TreeNode temp;
            temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if (curr.left != null)
                stack.add(curr.left);

            if (curr.right != null)
                stack.add(curr.right);
        }

        return root;
    }
}

class Test {
    public static void main(String[] args) {
        RecursiveSolution solution = new RecursiveSolution();
        int testsPassed = 0;
        int totalTests = 4;

        // Test case 1: Basic tree
        System.out.println("Test 1: Basic balanced tree");
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);

        TreeNode expected1 = new TreeNode(4);
        expected1.left = new TreeNode(7);
        expected1.right = new TreeNode(2);
        expected1.left.left = new TreeNode(9);
        expected1.left.right = new TreeNode(6);
        expected1.right.left = new TreeNode(3);
        expected1.right.right = new TreeNode(1);

        TreeNode result1 = solution.invertTree(root1);
        if (treesEqual(result1, expected1)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.print("Expected: "); printTree(expected1);
            System.out.print("Got: "); printTree(result1);
        }
        System.out.println();

        // Test case 2: Single node
        System.out.println("Test 2: Single node");
        TreeNode root2 = new TreeNode(1);
        TreeNode expected2 = new TreeNode(1);

        TreeNode result2 = solution.invertTree(root2);
        if (treesEqual(result2, expected2)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.print("Expected: "); printTree(expected2);
            System.out.print("Got: "); printTree(result2);
        }
        System.out.println();

        // Test case 3: Empty tree
        System.out.println("Test 3: Empty tree");
        TreeNode root3 = null;
        TreeNode expected3 = null;

        TreeNode result3 = solution.invertTree(root3);
        if (treesEqual(result3, expected3)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: null");
            System.out.print("Got: "); printTree(result3);
        }
        System.out.println();

        // Test case 4: Unbalanced tree
        System.out.println("Test 4: Unbalanced tree");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        root4.left.left.left = new TreeNode(4);

        TreeNode expected4 = new TreeNode(1);
        expected4.right = new TreeNode(2);
        expected4.right.right = new TreeNode(3);
        expected4.right.right.right = new TreeNode(4);

        TreeNode result4 = solution.invertTree(root4);
        if (treesEqual(result4, expected4)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.print("Expected: "); printTree(expected4);
            System.out.print("Got: "); printTree(result4);
        }
        System.out.println();

        // Summary
        System.out.println("=== TEST SUMMARY ===");
        System.out.println("Tests passed: " + testsPassed + "/" + totalTests);
        if (testsPassed == totalTests) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }

    // Helper method to compare two trees for equality
    public static boolean treesEqual(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return treesEqual(t1.left, t2.left) && treesEqual(t1.right, t2.right);
    }

    // Helper method to print tree (level order)
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // Remove trailing nulls for cleaner output
        while (!result.isEmpty() && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        System.out.println(result);
    }
}