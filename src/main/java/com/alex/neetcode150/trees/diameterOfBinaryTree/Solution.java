package com.alex.neetcode150.trees.diameterOfBinaryTree;

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

class Solution {
    int maxDiameter;    // Make this an instance variable rather than local, so we can update it from recursive calls

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;

        if (root == null)
            return 0;

        calcHeightAndDiameter(root);

        return maxDiameter;
    }

    public int calcHeightAndDiameter(TreeNode node) {
        if (node == null)
            return 0;

        int leftHeight = calcHeightAndDiameter(node.left);
        int rightHeight = calcHeightAndDiameter(node.right);

        // remember - diameter is number of "edges" in a path, rather than the number of nodes. So it's number of nodes -1
        int maxDiameterOfCurrentNode = leftHeight + rightHeight;
        maxDiameter = Math.max(maxDiameterOfCurrentNode, maxDiameter);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 7;

        // Test case 1: Basic tree (diameter passes through root)
        System.out.println("Test 1: Basic tree (diameter through root)");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);

        int expected1 = 3; // Path: 4 -> 2 -> 1 -> 3 (3 edges)
        int result1 = solution.diameterOfBinaryTree(root1);
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

        int expected2 = 0; // No edges in single node
        int result2 = solution.diameterOfBinaryTree(root2);
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
        int result3 = solution.diameterOfBinaryTree(root3);
        if (result3 == expected3) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3 + ", Got: " + result3);
        }
        System.out.println();

        // Test case 4: Linear tree (left-skewed)
        System.out.println("Test 4: Left-skewed tree");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        root4.left.left.left = new TreeNode(4);

        int expected4 = 3; // Path: 4 -> 3 -> 2 -> 1 (3 edges)
        int result4 = solution.diameterOfBinaryTree(root4);
        if (result4 == expected4) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4 + ", Got: " + result4);
        }
        System.out.println();

        // Test case 5: Diameter doesn't pass through root
        System.out.println("Test 5: Diameter doesn't pass through root");
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(4);
        root5.left.right = new TreeNode(5);
        root5.left.left.left = new TreeNode(8);
        root5.left.left.right = new TreeNode(9);
        root5.left.right.left = new TreeNode(10);
        root5.left.right.right = new TreeNode(11);

        int expected5 = 4; // Path: 8 -> 4 -> 2 -> 5 -> 11 (4 edges)
        int result5 = solution.diameterOfBinaryTree(root5);
        if (result5 == expected5) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5 + ", Got: " + result5);
        }
        System.out.println();

        // Test case 6: Two nodes (matches LeetCode example 2)
        System.out.println("Test 6: Two nodes");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);

        int expected6 = 1; // Path: 2 -> 1 (1 edge)
        int result6 = solution.diameterOfBinaryTree(root6);
        if (result6 == expected6) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6 + ", Got: " + result6);
        }
        System.out.println();

        // Test case 7: LeetCode Example 1 - [1,2,3,4,5]
        System.out.println("Test 7: LeetCode Example 1");
        TreeNode root7 = new TreeNode(1);
        root7.left = new TreeNode(2);
        root7.right = new TreeNode(3);
        root7.left.left = new TreeNode(4);
        root7.left.right = new TreeNode(5);

        int expected7 = 3; // Path: 4 -> 2 -> 1 -> 3 or 5 -> 2 -> 1 -> 3 (3 edges)
        int result7 = solution.diameterOfBinaryTree(root7);
        if (result7 == expected7) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7 + ", Got: " + result7);
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