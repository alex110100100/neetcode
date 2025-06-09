# 100 - Same Tree

[Problem Link](https://leetcode.com/problems/same-tree/)

## Problem Statement

Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

## Examples

### Example 1:
```
Input: p = [1,2,3], q = [1,2,3]
         1             1
        / \           / \
       2   3         2   3
Output: true
```

### Example 2:
```
Input: p = [1,2], q = [1,null,2]
         1             1
        /               \
       2                 2
Output: false
```

## Solution Approach

### Key Insight
- **Structural comparison**: Trees must have identical structure AND values
- **Recursive nature**: Trees are same if:
    1. Both roots have same value AND
    2. Left subtrees are same AND
    3. Right subtrees are same
- **Base cases**: Handle null combinations carefully

### Main Solution: Recursive Comparison
```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null)
        return true;
    
    if (p == null || q == null || p.val != q.val)
        return false;
    
    return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
}
```

**Why this works:**
- **Base case 1**: Both null → same (empty trees)
- **Base case 2**: One null, one not → different (structure mismatch)
- **Base case 3**: Different values → different
- **Recursive case**: Check subtrees recursively

## Logic Breakdown

### Condition Analysis
```java
if (p == null && q == null) return true;    // Both empty
if (p == null || q == null) return false;   // One empty, one not
if (p.val != q.val) return false;           // Different values
```

**Combined condition**: `p == null || q == null || p.val != q.val`
- If any of these is true → trees are different
- Only continue if both non-null AND same value



## Complexity Analysis

**Time Complexity:** O(min(m,n)) where m,n are number of nodes
- Visit nodes until difference found or all nodes compared
- Best case: O(1) if roots differ
- Worst case: O(min(m,n)) if trees identical or differ only at leaves

**Space Complexity:** O(min(h₁,h₂)) where h₁,h₂ are tree heights



**Time Complexity:** O(min(m,n))  
**Space Complexity:** O(min(h₁,h₂))