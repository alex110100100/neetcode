package com.alex.neetcode150.trees.maximumDepthOfBinaryTree;

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
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

class IterativeSolutionBFS {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int depth = 0;

        while (!q.isEmpty()) {
            depth++;
            int levelSize = q.size();   // number of nodes at current depth

            for (int i = 0; i < levelSize; i++) {   // process ALL nodes at current depth
                TreeNode curr = q.poll();

                if (curr.left != null)
                    q.add(curr.left);

                if (curr.right != null)
                    q.add(curr.right);
            }
        }
        return depth;
    }
}

class IterativeSolutionDFS {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int max = 0;

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.add(new Pair<>(root, 1));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> curr = stack.pop();

            max = Math.max(max, curr.second);

            if (curr.first.left != null)
                stack.push(new Pair<>(curr.first.left, curr.second + 1));

            if (curr.first.right != null)
                stack.push(new Pair<>(curr.first.right, curr.second + 1));

        }

        return max;
    }
}

class Pair<T, U> {
    public final T first;
    public final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}

class Test {
    public static void main(String[] args) {
        RecursiveSolution solution = new RecursiveSolution();
        int testsPassed = 0;
        int totalTests = 6;

        // Test case 1: Basic balanced tree
        System.out.println("Test 1: Basic balanced tree");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        int expected1 = 3;
        int result1 = solution.maxDepth(root1);
        if (result1 == expected1) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected1 + ", Got: " + result1);
        }
        System.out.println();

        // Test case 2: Single node
        System.out.println("Test 2: Single node");
        TreeNode root2 = new TreeNode(1);

        int expected2 = 1;
        int result2 = solution.maxDepth(root2);
        if (result2 == expected2) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2 + ", Got: " + result2);
        }
        System.out.println();

        // Test case 3: Empty tree
        System.out.println("Test 3: Empty tree");
        TreeNode root3 = null;

        int expected3 = 0;
        int result3 = solution.maxDepth(root3);
        if (result3 == expected3) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3 + ", Got: " + result3);
        }
        System.out.println();

        // Test case 4: Left-skewed tree
        System.out.println("Test 4: Left-skewed tree");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        root4.left.left.left = new TreeNode(4);
        root4.left.left.left.left = new TreeNode(5);

        int expected4 = 5;
        int result4 = solution.maxDepth(root4);
        if (result4 == expected4) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4 + ", Got: " + result4);
        }
        System.out.println();

        // Test case 5: Right-skewed tree
        System.out.println("Test 5: Right-skewed tree");
        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(2);
        root5.right.right = new TreeNode(3);

        int expected5 = 3;
        int result5 = solution.maxDepth(root5);
        if (result5 == expected5) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5 + ", Got: " + result5);
        }
        System.out.println();

        // Test case 6: Unbalanced tree with different depths
        System.out.println("Test 6: Unbalanced tree");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(3);
        root6.left.left = new TreeNode(4);
        root6.left.right = new TreeNode(5);
        root6.right.left = new TreeNode(6);
        root6.left.left.left = new TreeNode(7);
        root6.left.left.right = new TreeNode(8);
        root6.left.left.left.left = new TreeNode(9);

        int expected6 = 5;
        int result6 = solution.maxDepth(root6);
        if (result6 == expected6) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6 + ", Got: " + result6);
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
}