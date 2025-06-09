# 110 - Balanced Binary Tree

[Problem Link](https://leetcode.com/problems/balanced-binary-tree/)

## Problem Statement

Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

## Examples

### Example 1:
```
Input:     3           Output: true
         /   \         
        9     20       Heights: left=1, right=2, diff=1 ✓
             /  \      All subtrees also balanced
            15   7     
```

### Example 2:
```
Input:       1         Output: false
           /   \       
          2     2      Node 2 (left): left=2, right=0, diff=2 ✗
         / \            
        3   3           
       / \              
      4   4             
```

## Solution Approaches

### Key Insight
- **Balanced tree**: For EVERY node, |left_height - right_height| ≤ 1
- **Recursive nature**: Tree is balanced if:
    1. Current node is balanced AND
    2. Left subtree is balanced AND
    3. Right subtree is balanced

### Approach 1: Naive Recursive (Top-Down)
```java
public boolean isBalanced(TreeNode root) {
    if (root == null)
        return true;
    
    if (Math.abs(calcHeight(root.left) - calcHeight(root.right)) > 1)
        return false;
    
    return isBalanced(root.left) && isBalanced(root.right);
}

public int calcHeight(TreeNode node) {
    if (node == null)
        return 0;
    return Math.max(calcHeight(node.left), calcHeight(node.right)) + 1;
}
```

**Why this works but is inefficient:**
- Checks balance condition at each node
- Recursively checks all subtrees
- **Problem**: Recalculates heights multiple times for same nodes

### Approach 2: Optimized Single-Pass (Bottom-Up)
```java
class EfficientSolution {
    private boolean isBalanced;
    
    public boolean isBalanced(TreeNode root) {
        isBalanced = true;
        calcHeightAndCheckIfBalanced(root);
        return isBalanced;
    }
    
    public int calcHeightAndCheckIfBalanced(TreeNode node) {
        if (node == null)
            return 0;
        
        if (isBalanced == false)   // Early termination optimization
            return 0;
        
        int leftHeight = calcHeightAndCheckIfBalanced(node.left);
        int rightHeight = calcHeightAndCheckIfBalanced(node.right);
        
        if (Math.abs(leftHeight - rightHeight) > 1)
            isBalanced = false;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

**Why this is optimal:**
- **Single traversal**: Each node visited exactly once
- **Bottom-up**: Calculate heights from leaves upward
- **Early termination**: Stop as soon as imbalance found
- **Dual purpose**: Returns height while checking balance

### Alternative Approach 2: Return -1 for Imbalance
```java
public boolean isBalanced(TreeNode root) {
    return checkHeight(root) != -1;
}

private int checkHeight(TreeNode node) {
    if (node == null) return 0;
    
    int leftHeight = checkHeight(node.left);
    if (leftHeight == -1) return -1;  // Left subtree imbalanced
    
    int rightHeight = checkHeight(node.right);
    if (rightHeight == -1) return -1;  // Right subtree imbalanced
    
    // Check current node balance
    if (Math.abs(leftHeight - rightHeight) > 1) return -1;
    
    return Math.max(leftHeight, rightHeight) + 1;
}
```

**Pattern**: Use special return value (-1) to signal imbalance

## Complexity Analysis

**Time Complexity:**
- **Naive approach**: O(n²) in worst case
    - Each node calls calcHeight() which is O(n) in worst case
    - Skewed tree: O(n) nodes × O(n) height calculation = O(n²)
- **Optimized approach**: O(n) - each node visited exactly once

**Space Complexity:** O(h) where h = tree height
- Space from recursion call stack
- Best case (balanced): O(log n)
- Worst case (skewed): O(n)

## Key Concepts Learned

### Top-Down vs Bottom-Up Approaches
- **Top-Down (Naive)**: Start from root, calculate heights for each subtree
- **Bottom-Up (Optimal)**: Calculate heights from leaves up, check balance along the way
- **Bottom-up advantage**: Each height calculated once and reused

### Dual-Purpose Functions Pattern
- **Same pattern as diameter problem**: Calculate one thing, track another
- **Function returns**: Height of subtree
- **Side effect**: Updates global balance status
- **Alternative**: Return special values to signal states




**Time Complexity:** O(n) optimized, O(n²) naive  
**Space Complexity:** O(h)