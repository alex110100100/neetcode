# 150 - Evaluate Reverse Polish Notation

[Problem Link](https://leetcode.com/problems/evaluate-reverse-polish-notation/)

## Problem Statement

Evaluate the value of an arithmetic expression in Reverse Polish Notation (RPN).
Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note: Division between two integers should truncate toward zero.

## Examples

### Example 1:
```python
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
```

### Example 2:
```python
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
```

## Solution Approach

### Key Insight
- RPN eliminates need for parentheses by putting operators after operands
- Stack is perfect for tracking operands until operation needed

### Visual Example
```
Input: ["2","1","+","3","*"]

Step 1: "2"    Stack: [2]
Step 2: "1"    Stack: [2,1]
Step 3: "+"    Stack: [3]     // Pop 1,2 then push (2+1)
Step 4: "3"    Stack: [3,3]
Step 5: "*"    Stack: [9]     // Pop 3,3 then push (3*3)
Result: 9
```

### Implementation Details
1. Create Stack<Integer> for operands
2. For each token:
    - If number: Push to stack
    - If operator:
        - Pop two operands
        - Perform operation
        - Push result back
3. Final answer is last item on stack


**Time Complexity:** O(n)  
**Space Complexity:** O(n)

## Implementation Notes
- Be careful with order of operands for - and /
- Integer.parseInt() converts string to int
- Stack will never be empty during operations if input valid
- Final stack should contain exactly one number
