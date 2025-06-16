# 78 - Subsets

[Problem Link](https://leetcode.com/problems/subsets/)

## Problem Statement

Given an integer array `nums` of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

## Examples

### Example 1:
```
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```

### Example 2:
```
Input: nums = [0]
Output: [[],[0]]
```

## Solution Approach

### Key Insight
- **Backtracking pattern**: For each element, we have 2 choices - include it or skip it
- **Generate all combinations**: Explore all possible ways to build subsets
- **Decision tree**: At each step, branch on "take current element" vs "move to next"

### Main Solution: Backtracking
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currentSubset = new ArrayList<>();
    backtrack(result, currentSubset, nums, 0);
    return result;
}

public void backtrack(List<List<Integer>> result, List<Integer> currentSubset, 
                     int[] nums, int index) {
    result.add(new ArrayList<>(currentSubset));
    
    for (int i = index; i < nums.length; i++) {
        // Make a choice (add number to subset)
        currentSubset.add(nums[i]);
        
        // Recurse with current choice
        backtrack(result, currentSubset, nums, i + 1);
        
        // Backtrack: undo the current choice
        currentSubset.remove(currentSubset.size() - 1);
    }
}
```

**Why this works:**
- **Add current state**: Every recursive call represents a valid subset
- **For loop**: Try including each remaining element
- **Recursive call**: Build subsets that include current element
- **Backtrack**: Remove element to try other possibilities

## Algorithm Walkthrough

### Example: nums = [1,2,3]
```
Call Stack Level 0: backtrack(currentSubset=[], index=0)
  add([]) to result
  
  i=0: currentSubset.add(1) → currentSubset=[1]
  Call Stack Level 1: backtrack(currentSubset=[1], index=1)
    add([1]) to result
    
    i=1: currentSubset.add(2) → currentSubset=[1,2]
    Call Stack Level 2: backtrack(currentSubset=[1,2], index=2)
      add([1,2]) to result
      
      i=2: currentSubset.add(3) → currentSubset=[1,2,3]
      Call Stack Level 3: backtrack(currentSubset=[1,2,3], index=3)
        add([1,2,3]) to result
        (no more elements, return)
      Level 3 ends → back to Level 2
      currentSubset.remove(3) → currentSubset=[1,2]
      
      (no more i values, return)
    Level 2 ends → back to Level 1
    currentSubset.remove(2) → currentSubset=[1]
    
    i=2: currentSubset.add(3) → currentSubset=[1,3]
    Call Stack Level 2: backtrack(currentSubset=[1,3], index=3)
      add([1,3]) to result
      (no more elements, return)
    Level 2 ends → back to Level 1
    currentSubset.remove(3) → currentSubset=[1]
    
    (no more i values, return)
  Level 1 ends → back to Level 0
  currentSubset.remove(1) → currentSubset=[]
  
  i=1: currentSubset.add(2) → currentSubset=[2]
  Call Stack Level 1: backtrack(currentSubset=[2], index=2)
    add([2]) to result
    
    i=2: currentSubset.add(3) → currentSubset=[2,3]
    Call Stack Level 2: backtrack(currentSubset=[2,3], index=3)
      add([2,3]) to result
      (no more elements, return)
    Level 2 ends → back to Level 1
    currentSubset.remove(3) → currentSubset=[2]
    
    (no more i values, return)
  Level 1 ends → back to Level 0
  currentSubset.remove(2) → currentSubset=[]
  
  i=2: currentSubset.add(3) → currentSubset=[3]
  Call Stack Level 1: backtrack(currentSubset=[3], index=3)
    add([3]) to result
    (no more elements, return)
  Level 1 ends → back to Level 0
  currentSubset.remove(3) → currentSubset=[]

Result: [[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
```

## Complexity Analysis

**Time Complexity:** O(n × 2ⁿ)
- 2ⁿ total subsets (each element can be included or excluded)
- O(n) to copy each subset to result list
- Total: O(n × 2ⁿ)

**Space Complexity:** O(n)
- Recursion depth: O(n) maximum
- Current subset size: O(n) maximum
- Result storage not counted in space complexity

## Key Concepts Learned

### Backtracking Template
```java
public void backtrack(parameters) {
    // Add current state to result (if valid)
    result.add(new ArrayList<>(currentState));
    
    // Try all possible choices
    for (choice in choices) {
        // Make choice
        currentState.add(choice);
        
        // Recurse with choice made
        backtrack(updatedParameters);
        
        // Undo choice (backtrack)
        currentState.remove(choice);
    }
}
```

### Index Parameter Purpose
- **Prevents duplicates**: Only consider elements from current index forward
- **Maintains order**: Ensures subsets like [1,3] and [3,1] don't both appear
- **Progressive selection**: Each recursive level considers fewer elements

### Copy vs Reference
```java
// WRONG: Adds reference to same list
result.add(currentSubset);

// CORRECT: Adds copy of current state
result.add(new ArrayList<>(currentSubset));
```

### Why Add Before Loop?
- **Every state is valid**: Even empty subset is a valid result
- **Include all possibilities**: Covers case where we don't add any more elements
- **Natural base case**: No additional stopping condition needed

## Implementation Notes

### Alternative Approach: Iterative (Bit Manipulation)
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    int n = nums.length;
    
    // Generate all numbers from 0 to 2^n - 1
    for (int i = 0; i < (1 << n); i++) {
        List<Integer> subset = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            // Check if jth bit is set
            if ((i & (1 << j)) != 0) {
                subset.add(nums[j]);
            }
        }
        result.add(subset);
    }
    return result;
}
```

### Common Backtracking Mistakes
```java
// MISTAKE 1: Forgetting to backtrack
currentSubset.add(nums[i]);
backtrack(...);
// Missing: currentSubset.remove(currentSubset.size() - 1);

// MISTAKE 2: Wrong index progression
backtrack(result, currentSubset, nums, index + 1); // Should be i + 1

// MISTAKE 3: Not copying result
result.add(currentSubset); // Wrong - adds reference
```

### When to Use Backtracking
- **Generate all combinations/permutations**: Subsets, combinations, permutations
- **Constraint satisfaction**: N-Queens, Sudoku solver
- **Path finding**: Find all paths, word search
- **Optimization**: When you need to explore all possibilities

### Backtracking vs Other Approaches
- **Backtracking**: Natural for exploration problems, easier to understand
- **Bit manipulation**: More efficient for subsets specifically, harder to generalize
- **Iterative**: Can be more memory efficient, but often more complex logic

**Time Complexity:** O(n × 2ⁿ)  
**Space Complexity:** O(n)