# 39 - Combination Sum

[Problem Link](https://leetcode.com/problems/combination-sum/)

## Problem Statement

Given an array of distinct integers `candidates` and a target integer `target`, return a list of all unique combinations of `candidates` where the chosen numbers sum to `target`. You may return the combinations in any order.

The same number may be chosen from `candidates` an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

## Examples

### Example 1:
```
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation: 2 and 3 can be used multiple times.
```

### Example 2:
```
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
```

## Solution Approach

### Key Insight
- **Backtracking with repetition**: Same number can be used multiple times
- **Target tracking**: Subtract each choice from remaining target
- **Index management**: Allow reusing current number (pass `i`, not `i+1`)

### Main Solution: Backtracking
```java
public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currCombo = new ArrayList<>();
    backtrack(result, currCombo, nums, target, 0);
    return result;
}

public void backtrack(List<List<Integer>> result, List<Integer> currCombo, 
                     int[] nums, int targetRemaining, int startIndex) {
    // Base case: found valid combination
    if (targetRemaining == 0) {
        result.add(new ArrayList<>(currCombo));
        return;
    }
    
    // Base case: overshot target
    if (targetRemaining < 0)
        return;
    
    for (int i = startIndex; i < nums.length; i++) {
        // Make choice
        currCombo.add(nums[i]);
        
        // Recurse: allow reusing current number (pass i, not i+1)
        backtrack(result, currCombo, nums, targetRemaining - nums[i], i);
        
        // Undo choice
        currCombo.remove(currCombo.size() - 1);
    }
}
```

**Key differences from Subsets:**
- **Target parameter**: Track remaining target instead of just generating all combinations
- **Reuse allowed**: Pass `i` instead of `i+1` to allow repeated use of same number
- **Early termination**: Stop when target becomes negative

## Algorithm Walkthrough

### Example: candidates = [2,3], target = 7
```
Call Stack Level 0: backtrack(currCombo=[], targetRemaining=7, startIndex=0)
  
  i=0 (nums[0]=2): currCombo.add(2) → currCombo=[2]
  Call Stack Level 1: backtrack(currCombo=[2], targetRemaining=5, startIndex=0)
    
    i=0 (nums[0]=2): currCombo.add(2) → currCombo=[2,2]
    Call Stack Level 2: backtrack(currCombo=[2,2], targetRemaining=3, startIndex=0)
      
      i=0 (nums[0]=2): currCombo.add(2) → currCombo=[2,2,2]
      Call Stack Level 3: backtrack(currCombo=[2,2,2], targetRemaining=1, startIndex=0)
        
        i=0 (nums[0]=2): currCombo.add(2) → currCombo=[2,2,2,2]
        Call Stack Level 4: backtrack(currCombo=[2,2,2,2], targetRemaining=-1, startIndex=0)
          targetRemaining < 0, return
        Level 4 ends → back to Level 3
        currCombo.remove(2) → currCombo=[2,2,2]
        
        i=1 (nums[1]=3): currCombo.add(3) → currCombo=[2,2,2,3]
        Call Stack Level 4: backtrack(currCombo=[2,2,2,3], targetRemaining=-2, startIndex=1)
          targetRemaining < 0, return
        Level 4 ends → back to Level 3
        currCombo.remove(3) → currCombo=[2,2,2]
        
      Level 3 ends → back to Level 2
      currCombo.remove(2) → currCombo=[2,2]
      
      i=1 (nums[1]=3): currCombo.add(3) → currCombo=[2,2,3]
      Call Stack Level 3: backtrack(currCombo=[2,2,3], targetRemaining=0, startIndex=1)
        targetRemaining == 0, add [2,2,3] to result ✓
        return
      Level 3 ends → back to Level 2
      currCombo.remove(3) → currCombo=[2,2]
      
    Level 2 ends → back to Level 1
    currCombo.remove(2) → currCombo=[2]
    
    i=1 (nums[1]=3): currCombo.add(3) → currCombo=[2,3]
    Call Stack Level 2: backtrack(currCombo=[2,3], targetRemaining=2, startIndex=1)
      
      i=1 (nums[1]=3): currCombo.add(3) → currCombo=[2,3,3]
      Call Stack Level 3: backtrack(currCombo=[2,3,3], targetRemaining=-1, startIndex=1)
        targetRemaining < 0, return
      Level 3 ends → back to Level 2
      currCombo.remove(3) → currCombo=[2,3]
      
    Level 2 ends → back to Level 1
    currCombo.remove(3) → currCombo=[2]
    
  Level 1 ends → back to Level 0
  currCombo.remove(2) → currCombo=[]
  
  i=1 (nums[1]=3): currCombo.add(3) → currCombo=[3]
  Call Stack Level 1: backtrack(currCombo=[3], targetRemaining=4, startIndex=1)
    
    i=1 (nums[1]=3): currCombo.add(3) → currCombo=[3,3]
    Call Stack Level 2: backtrack(currCombo=[3,3], targetRemaining=1, startIndex=1)
      
      i=1 (nums[1]=3): currCombo.add(3) → currCombo=[3,3,3]
      Call Stack Level 3: backtrack(currCombo=[3,3,3], targetRemaining=-2, startIndex=1)
        targetRemaining < 0, return
      Level 3 ends → back to Level 2
      currCombo.remove(3) → currCombo=[3,3]
      
    Level 2 ends → back to Level 1
    currCombo.remove(3) → currCombo=[3]
    
  Level 1 ends → back to Level 0
  currCombo.remove(3) → currCombo=[]

Result: [[2,2,3]]
```

## Complexity Analysis

**Time Complexity:** O(N^(T/M))
- N = number of candidates
- T = target value
- M = minimal value among candidates
- Each path in recursion tree can be at most T/M deep
- Each level has at most N choices

**Space Complexity:** O(T/M)
- Maximum recursion depth is T/M (when using smallest candidate repeatedly)
- Current combination length at most T/M

## Key Concepts Learned

### Repetition vs No Repetition
```java
// Subsets (no repetition): pass i+1
backtrack(result, currentSubset, nums, i + 1);

// Combination Sum (allow repetition): pass i
backtrack(result, currCombo, nums, targetRemaining - nums[i], i);
```

### Target Tracking Pattern
- **Subtract from target**: More intuitive than tracking running sum
- **Base cases**: `targetRemaining == 0` (success) and `targetRemaining < 0` (failure)
- **Early termination**: Prune branches that can't possibly succeed

### StartIndex Purpose
- **Prevents duplicates**: [2,3] and [3,2] are considered same combination
- **Maintains order**: Only consider candidates from current index forward
- **Allows repetition**: Current number can be reused (don't increment to i+1)

### Optimization Opportunity
```java
// Optional: Sort array first for early termination
Arrays.sort(candidates);
// Then in backtracking, if nums[i] > targetRemaining, break (all remaining will be larger)
```
