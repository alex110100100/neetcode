# 572 - Subtree of Another Tree

[Problem Link](https://leetcode.com/problems/subtree-of-another-tree/)

## Problem Statement

Given the roots of two binary trees `root` and `subRoot`, return `true` if there is a subtree of `root` with the same structure and node values of `subRoot` and `false` otherwise.

A subtree of a binary tree `tree` is a tree that consists of a node in `tree` and all of this node's descendants. The tree `tree` could also be considered as a subtree of itself.

## Examples

### Example 1:
```
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
         3                     4
        / \                   / \
       4   5                 1   2
      / \
     1   2
Output: true
```

### Example 2:
```
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
         3                     4
        / \                   / \
       4   5                 1   2
      / \
     1   2
        /
       0
Output: false (subtree has extra node 0)
```

## Solution Approaches

### Key Insight
- **Subtree check**: For each node in main tree, check if subtree starting there matches subRoot
- **Two-step process**:
    1. Find potential starting points in main tree
    2. Check if tree rooted at each point matches subRoot exactly

### Approach 1: Recursive with Same Tree Check
```java
public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (subRoot == null)
        return true;
    
    if (root == null)
        return false;
    
    if (isSameTree(root, subRoot))
        return true;
    
    return (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
}

public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null)
        return true;
    
    if (p == null || q == null || p.val != q.val)
        return false;
    
    return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
}
```

**Why this works:**
- **Base cases**: null subRoot = always subtree, null root = never subtree
- **Check current node**: Use isSameTree to see if subtree starts here
- **Recurse**: If not found here, check left and right subtrees
- **OR logic**: Found in either left OR right subtree means found overall

### Approach 2: String Serialization
```java
public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    return serialise(root).contains(serialise(subRoot));
}

public String serialise(TreeNode root) {
    if (root == null)
        return "null,";
    
    return root.val + ',' + serialise(root.left) + serialise(root.right);
}
```

**Why this works:**
- **Serialize both trees**: Convert to string representation
- **Substring check**: If subRoot string is substring of root string, it's a subtree
- **Preorder traversal**: Ensures structure is preserved in string

## Complexity Analysis

**Time Complexity:**
- **Recursive approach**: O(m × n) where m = nodes in root, n = nodes in subRoot
    - For each of m nodes, potentially run isSameTree which takes O(n)
- **Serialization approach**: O(m + n)
    - O(m) to serialize root, O(n) to serialize subRoot, O(m) for contains check

**Space Complexity:**
- **Recursive approach**: O(max(h₁, h₂)) where h₁, h₂ are tree heights
- **Serialization approach**: O(m + n) for storing serialized strings

## Key Concepts Learned

### Base Case Handling
```java
if (subRoot == null) return true;    // Empty tree is subtree of any tree
if (root == null) return false;      // Non-empty tree can't be subtree of empty tree
```


### Serialization vs Recursive Trade-offs
- **Recursive**: More intuitive, handles edge cases naturally
- **Serialization**: Potentially faster for large trees, but needs careful delimiter handling
- **Choice**: Recursive approach generally preferred for clarity

**Time Complexity:** O(m × n) recursive, O(m + n) serialization  
**Space Complexity:** O(h) recursive, O(m + n) serialization