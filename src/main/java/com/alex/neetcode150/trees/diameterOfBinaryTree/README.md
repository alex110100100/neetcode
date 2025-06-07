# 543 - Diameter of Binary Tree

[Problem Link](https://leetcode.com/problems/diameter-of-binary-tree/)

## Problem Statement

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

## Examples

### Example 1:
```
Input:     1           Output: 3
         /   \         
        2     3        Longest path: 4 → 2 → 1 → 3 (3 edges)
       / \             Or: 5 → 2 → 1 → 3 (3 edges)
      4   5            
```

### Example 2:
```
Input: [1,2]    Output: 1
```

## Solution Approach

### Key Insight
- **Diameter at any node** = left subtree height + right subtree height
- The longest path through a node connects the deepest leaf in left subtree to deepest leaf in right subtree
- **Global problem**: Check diameter at EVERY node, track maximum
- **Efficient approach**: Calculate height and diameter simultaneously in one traversal

### Main Solution: Height Calculation with Diameter Tracking
```java
class Solution {
    int maxDiameter;    // Instance variable to track global maximum
    
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
        
        // Diameter through current node = left height + right height
        int maxDiameterOfCurrentNode = leftHeight + rightHeight;
        maxDiameter = Math.max(maxDiameterOfCurrentNode, maxDiameter);
        
        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
```

**Why this works:**
- **Dual purpose function**: Returns height but also updates global diameter
- **Key insight**: Diameter through any node = sum of heights of its subtrees
- **Bottom-up approach**: Calculate subtree heights first, then use them for diameter

### Alternative: Separate Height Function (Less Efficient)
```java
public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) return 0;
    
    // Diameter through root
    int diameterThroughRoot = height(root.left) + height(root.right);
    
    // Check diameters in subtrees
    int leftDiameter = diameterOfBinaryTree(root.left);
    int rightDiameter = diameterOfBinaryTree(root.right);
    
    return Math.max(diameterThroughRoot, Math.max(leftDiameter, rightDiameter));
}

private int height(TreeNode node) {
    if (node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
}
```

**Why this is inefficient**: Recalculates height multiple times for same nodes → O(n²) time

## Complexity Analysis

**Time Complexity:**
- **Optimized solution**: O(n) - visit each node exactly once
- **Naive solution**: O(n²) - recalculate heights multiple times

**Space Complexity:** O(h) where h = tree height
- Space from recursion call stack
- Best case (balanced): O(log n)
- Worst case (skewed): O(n)

## Key Concepts Learned

### Diameter vs Height Relationship
- **Height**: Longest path from node to any leaf (number of edges)
- **Diameter through node**: Left height + Right height
- **Global diameter**: Maximum diameter among all nodes

### Edge Count vs Node Count
- **Problem asks for edges**: Path length = number of edges
- **Height returns edges**: Height 0 = leaf node, Height 1 = one edge to leaf
- **Diameter = leftHeight + rightHeight** (already in edge count)

### Dual-Purpose Recursive Functions
- **Pattern**: Function returns one value but updates global state
- **Benefit**: Single traversal instead of multiple
- **Common in tree problems**: Calculate property while computing another

### Instance Variable vs Return Value
- **Instance variable**: Tracks global maximum across all recursive calls
- **Return value**: Used for recursive computation (height in this case)
- **Alternative**: Pass by reference using arrays/objects


## Tree Diameter Intuition

Think of diameter as "stretching" the tree:
- Take the deepest leaf in left subtree
- Connect it through current node to deepest leaf in right subtree
- This path length = leftHeight + rightHeight edges
- Check this for every possible "connection point" (every node)

**Time Complexity:** O(n)  
**Space Complexity:** O(h)