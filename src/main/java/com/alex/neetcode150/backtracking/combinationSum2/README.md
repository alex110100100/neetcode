# 40 - Combination Sum II

[Problem Link](https://leetcode.com/problems/combination-sum-ii/)

## Problem Statement

Given a collection of candidate numbers (`candidates`) and a target number (`target`), find all unique combinations in `candidates` where the candidate numbers sum to `target`.

Each number in `candidates` may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

## Examples

### Example 1:
```
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
```

### Example 2:
```
Input: candidates = [2,5,2,1,2], target = 5
Output: [[1,2,2],[5]]
```

## Solution Approaches

### Key Differences from Combination Sum I
- **No repetition**: Each element can only be used once (pass `i+1`, not `i`)
- **Duplicate elements**: Array may contain duplicates, but result must have unique combinations
- **Skip strategy**: Need to avoid duplicate combinations while allowing duplicate elements

### Approach 1: HashSet for Duplicate Prevention
```java
public List<List<Integer>> combinationSum2(int[] nums, int target) {
    Arrays.sort(nums);
    Set<List<Integer>> result = new HashSet<>();
    List<Integer> currCombo = new ArrayList<>();
    
    backtrack(result, currCombo, nums, target, 0);
    return new ArrayList<>(result);
}

public void backtrack(Set<List<Integer>> result, List<Integer> currCombo, 
                     int[] nums, int targetRemaining, int startIndex) {
    if (targetRemaining == 0) {
        result.add(new ArrayList<>(currCombo));
        return;
    }
    
    if (targetRemaining < 0)
        return;
    
    for (int i = startIndex; i < nums.length; i++) {
        currCombo.add(nums[i]);
        backtrack(result, currCombo, nums, targetRemaining - nums[i], i + 1);
        currCombo.remove(currCombo.size() - 1);
    }
}
```

**Pros:** Simple modification of Combination Sum I  
**Cons:** Generates duplicates then filters them out (less efficient)

### Approach 2: Skip Duplicates at Same Level (Optimal)
```java
public List<List<Integer>> combinationSum2(int[] nums, int target) {
    Arrays.sort(nums); // MUST be sorted so duplicates are adjacent
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currCombo = new ArrayList<>();
    
    backtrack(result, currCombo, nums, target, 0);
    return result;
}

public void backtrack(List<List<Integer>> result, List<Integer> currCombo, 
                     int[] nums, int targetRemaining, int startIndex) {
    if (targetRemaining == 0) {
        result.add(new ArrayList<>(currCombo));
        return;
    }
    
    if (targetRemaining < 0)
        return;
    
    for (int i = startIndex; i < nums.length; i++) {
        // KEY INSIGHT: Skip duplicates at the same recursion level
        if (i > startIndex && nums[i] == nums[i - 1])
            continue; // Skip this duplicate
        
        currCombo.add(nums[i]);
        backtrack(result, currCombo, nums, targetRemaining - nums[i], i + 1);
        currCombo.remove(currCombo.size() - 1);
    }
}
```

**Why this works:** Prevents exploring identical branches at the same recursion level

## Key Insight: Skip Condition Explained

### Why `i > startIndex` is Crucial
```java
if (i > startIndex && nums[i] == nums[i - 1])
    continue;
```

**Sorted array: [1,1,2,5,6,7,10], target = 8**

At recursion level 0 (startIndex = 0):
- i = 0: Use first 1 ✓ (explore branch [1,...])
- i = 1: Skip second 1 ✗ (would create duplicate branch [1,...])

At recursion level 1 after choosing first 1 (startIndex = 1):
- i = 1: Use second 1 ✓ (this is fine, continuing same branch [1,1,...])
- i = 2: Use 2 ✓

**The rule:** Skip duplicates only when `i > startIndex` (not the first occurrence at this level)

### Algorithm Walkthrough: [1,1,2], target = 3
```
Call Stack Level 0: backtrack(currCombo=[], targetRemaining=3, startIndex=0)
  
  i=0 (nums[0]=1): i == startIndex, so don't skip
  currCombo.add(1) → currCombo=[1]
  Call Stack Level 1: backtrack(currCombo=[1], targetRemaining=2, startIndex=1)
    
    i=1 (nums[1]=1): i == startIndex, so don't skip (this is the second 1, but first at this level)
    currCombo.add(1) → currCombo=[1,1]
    Call Stack Level 2: backtrack(currCombo=[1,1], targetRemaining=1, startIndex=2)
      
      i=2 (nums[2]=2): currCombo.add(2) → currCombo=[1,1,2]
      Call Stack Level 3: backtrack(currCombo=[1,1,2], targetRemaining=-1, startIndex=3)
        targetRemaining < 0, return
      Level 3 ends → back to Level 2
      currCombo.remove(2) → currCombo=[1,1]
      
    Level 2 ends → back to Level 1
    currCombo.remove(1) → currCombo=[1]
    
    i=2 (nums[2]=2): currCombo.add(2) → currCombo=[1,2]
    Call Stack Level 2: backtrack(currCombo=[1,2], targetRemaining=0, startIndex=3)
      targetRemaining == 0, add [1,2] to result ✓
      return
    Level 2 ends → back to Level 1
    currCombo.remove(2) → currCombo=[1]
    
  Level 1 ends → back to Level 0
  currCombo.remove(1) → currCombo=[]
  
  i=1 (nums[1]=1): i > startIndex && nums[1] == nums[0], so SKIP this duplicate
  
  i=2 (nums[2]=2): currCombo.add(2) → currCombo=[2]
  Call Stack Level 1: backtrack(currCombo=[2], targetRemaining=1, startIndex=3)
    (no more elements, return)
  Level 1 ends → back to Level 0
  currCombo.remove(2) → currCombo=[]

Result: [[1,2]]
```

Notice how we skip the second 1 at level 0 but use it at level 1!

## Complexity Analysis

**Time Complexity:** O(2^n) in worst case
- Each element can be included or excluded
- Duplicate skipping reduces actual branches explored

**Space Complexity:** O(target/min_value)
- Maximum recursion depth
- Space for storing current combination

## Key Concepts Learned

### Duplicate Handling Strategies
1. **Generate then filter**: Use HashSet (simple but inefficient)
2. **Skip during generation**: Check `i > startIndex` (optimal)

### Why Sorting is Essential
```java
Arrays.sort(nums); // Required for skip logic to work
```
- **Groups duplicates**: All identical values become adjacent
- **Skip condition**: Can compare with previous element `nums[i-1]`
- **Without sorting**: Duplicate values scattered, can't efficiently skip

### Level vs Position Distinction
- **Same level**: Different choices at same recursion depth
- **Different levels**: Choices in different recursive calls
- **Skip rule**: Only skip duplicates at same level, not across levels

### Combination Sum Variations Summary
```java
// Combination Sum I: Unlimited reuse
backtrack(result, currCombo, nums, targetRemaining - nums[i], i);

// Combination Sum II: Each element once, skip duplicates
if (i > startIndex && nums[i] == nums[i-1]) continue;
backtrack(result, currCombo, nums, targetRemaining - nums[i], i + 1);
```


**Time Complexity:** O(2^n)  
**Space Complexity:** O(target/min_value)