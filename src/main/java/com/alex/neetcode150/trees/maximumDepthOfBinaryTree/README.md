# 104 - Maximum Depth of Binary Tree

[Problem Link](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

## Problem Statement

Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

## Examples

### Example 1:
```
Input:     3           Output: 3
         /   \         
        9     20       Longest path: 3 → 20 → 15 (or 3 → 20 → 7)
             /  \      
            15   7     
```

### Example 2:
```
Input: [1,null,2]    Output: 2
```

## Solution Approaches

### Key Insight
- Max depth = length of longest root-to-leaf path
- Can think recursively: depth = 1 + max(left_depth, right_depth)
- Can also track depth while traversing iteratively

### Approach 1: Recursive (DFS)
```java
public int maxDepth(TreeNode root) {
    if (root == null)
        return 0;
    
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

**Why this works**:
- Base case: null node has depth 0
- Recursive case: current depth = 1 (current node) + max depth of subtrees
- Natural divide-and-conquer approach

### Approach 2: Iterative BFS (Level-by-Level)
```java
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
```

**BFS Key Technique**: Process entire levels at once
- `levelSize = q.size()` captures nodes at current level
- Process exactly that many nodes before moving to next level
- Increment depth after processing each complete level

### Approach 3: Iterative DFS (Stack with Depth Tracking)
```java
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
```

**DFS Pattern with State**:
- Store (node, depth) pairs instead of just nodes
- Track maximum depth seen so far
- Increment depth when adding children to stack

## Complexity Analysis

**Time Complexity:** O(n) for all approaches
- Must visit every node at least once to find maximum depth
- Each node processed exactly once

**Space Complexity:**
- **Recursive**: O(h) where h = tree height
    - Best case (balanced): O(log n)
    - Worst case (skewed): O(n)
    - Space from recursion call stack
- **Iterative BFS**: O(w) where w = maximum width of tree
    - Worst case: O(n) for complete binary tree (bottom level has ~n/2 nodes)
- **Iterative DFS**: O(h) - same as recursive but explicit stack

## Key Concepts Learned

### Level-by-Level BFS Processing
- **Critical Pattern**: `int levelSize = q.size()` before inner loop
- Process exactly `levelSize` nodes to handle one complete level
- This prevents mixing nodes from different levels in the queue

### State Tracking in DFS
- When iterative DFS needs extra info, store (node, state) pairs
- Common states: depth, path, parent reference
- Alternative: use two parallel stacks (one for nodes, one for depths)

### Recursive Tree Height Formula
- Height of tree = 1 + max(height of left subtree, height of right subtree)
- Base case: height of null tree = 0
- This formula works for any tree measurement (depth, height, etc.)

### Queue vs Stack Behavior
- **BFS (Queue)**: Processes nodes in order they were discovered → level-by-level
- **DFS (Stack)**: Processes most recently discovered node first → go deep

## Implementation Notes

### Pair Class Alternative
Instead of custom Pair class, could use:
```java
// Option 1: Two parallel stacks
Stack<TreeNode> nodeStack = new Stack<>();
Stack<Integer> depthStack = new Stack<>();

// Option 2: Array/Object wrapper
Stack<Object[]> stack = new Stack<>();
stack.push(new Object[]{root, 1});
```

### Method Selection
- **Recursive**: Most intuitive, clean code
- **BFS**: Good for understanding tree structure level-by-level
- **DFS Iterative**: Useful when recursion depth is a concern

**Time Complexity:** O(n)  
**Space Complexity:** O(h) recursive/DFS, O(w) BFS