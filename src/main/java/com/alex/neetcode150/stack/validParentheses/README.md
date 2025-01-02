# 20 - Valid Parentheses

[Problem Link](https://leetcode.com/problems/valid-parentheses/description/)

## Problem Statement

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

### Validity Rules
- Open brackets must be closed by same type
- Open brackets must be closed in correct order
- Every close bracket needs matching open bracket

## Examples

### Example 1:
```python
Input: s = "()"
Output: true
```

### Example 2:
```python
Input: s = "()[]{}"
Output: true
```

### Example 3:
```python
Input: s = "(]"
Output: false
```

## Solution Approach

### Using Stack
Use a stack to track opening brackets and verify closing brackets match in correct order.

### Visual Example
```
Input: "({[]})"
Stack changes:
(    -> [(]
({   -> [(, {]
({[  -> [(, {, []
({[] -> [(, {]    ✓ ] matches
({   -> [(]       ✓ } matches
(    -> []        ✓ ) matches
     -> valid (empty stack)
```

### Implementation Details
1. Create Stack<Character>
2. Use switch statement for each char:
    - Opening brackets: Push to stack
    - Closing brackets:
        - Check if stack empty (invalid)
        - Pop and compare with expected opener
3. Final check: stack must be empty

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

## Implementation Notes
- Switch statement provides clean handling of each case
- Short-circuit logic with isEmpty() check
- No need for HashMap of bracket pairs
- Early return on any mismatch