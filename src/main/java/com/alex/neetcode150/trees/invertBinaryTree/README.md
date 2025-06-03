# 226 - Invert Binary Tree

[Problem Link](https://leetcode.com/problems/invert-binary-tree/)

## Problem Statement

Given the root of a binary tree, invert the tree, and return its root.
Inverting means swapping the left and right children of all nodes in the tree.

## Examples

### Example 1:
```
Input:     4           Output:    4
         /   \                  /   \
        2     7                7     2
       / \   / \              / \   / \
      1   3 6   9            9   6 3   1
```

### Example 2:
```
Input: [2,1,3]    Output: [2,3,1]
```

## Solution Approaches

### Key Insight
- Invert tree = swap left and right children of EVERY node
- Can be done recursively (most intuitive) or iteratively
- Process each node once, swap its children, then handle subtrees

### Approach 1: Recursive (DFS)
```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    
    // Swap children of current node
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
    
    // Recursively invert subtrees
    invertTree(root.left);
    invertTree(root.right);
    
    return root;
}
```

**Why this works**: At each node, swap its children, then recursively handle the subtrees. Base case handles null nodes.

### Approach 2: Iterative BFS (Queue)
```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        TreeNode current = queue.poll();
        
        // Swap children
        TreeNode temp = current.left;
        current.left = current.right;
        current.right = temp;
        
        // Add children for processing
        if (current.left != null) queue.offer(current.left);
        if (current.right != null) queue.offer(current.right);
    }
    
    return root;
}
```

**BFS Pattern**: Process nodes level by level using queue (FIFO)

### Approach 3: Iterative DFS (Stack)
```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    
    while (!stack.isEmpty()) {
        TreeNode current = stack.pop();
        
        // Swap children
        TreeNode temp = current.left;
        current.left = current.right;
        current.right = temp;
        
        // Add children for processing
        if (current.left != null) stack.push(current.left);
        if (current.right != null) stack.push(current.right);
    }
    
    return root;
}
```

**DFS Pattern**: Go deep first using stack (LIFO)

## Complexity Analysis

**Time Complexity:** O(n) for all approaches
- Visit each node exactly once
- Constant work per node (swap two pointers)

**Space Complexity:**
- **Recursive**: O(h) where h = tree height
    - Best case (balanced): O(log n)
    - Worst case (skewed): O(n)
    - Space from recursion call stack
- **Iterative BFS**: O(w) where w = maximum width of tree
    - Can be up to O(n) for complete trees
- **Iterative DFS**: O(h) - same as recursive but explicit stack

## Key Concepts Learned

### DFS vs BFS Data Structures
- **DFS uses Stack**: LIFO → go deep first
- **BFS uses Queue**: FIFO → go wide first
- Both patterns fundamental for tree/graph traversal

### Tree Height in Balanced Trees
- Balanced tree height ≈ log₂(n)
- Each level roughly doubles the nodes
- Why: 2⁰ + 2¹ + 2² + ... + 2ʰ ≈ 2^(h+1) = n nodes

### Queue Methods
- `offer()` vs `add()`: offer() returns false on failure, add() throws exception
- `poll()` vs `remove()`: poll() returns null if empty, remove() throws exception
- For unlimited capacity collections like LinkedList, they behave the same

## Implementation Notes
- All three approaches are valid; recursive is most intuitive
- Recursive preferred unless worried about stack overflow on very deep trees
- Same swapping pattern in all: temp = left, left = right, right = temp
- Don't forget null checks!

**Time Complexity:** O(n)  
**Space Complexity:** O(h) recursive, O(w) BFS, O(h) DFS iterative