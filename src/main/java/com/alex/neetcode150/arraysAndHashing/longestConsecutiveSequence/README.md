# 128 - Longest Consecutive Sequence

[Problem Link](https://leetcode.com/problems/longest-consecutive-sequence/description/)

## Problem Statement

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

### Constraint
You must write an algorithm that runs in O(n) time.

## Examples

### Example 1:
```python
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
```

### Example 2:
```python
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
```

## Solution Approach

### Key Insights
- A sequence start number n will never have n-1 present in the array
- Subsequences don't need to be considered when finding the longest sequence
- Numbers don't need to be physically adjacent in the input array

### Implementation Strategy
1. Create HashSet from input array for O(1) lookups
2. For each number in the set:
    - Check if it's a sequence start (n-1 not present)
    - If it is, count sequence length by checking for consecutive numbers
    - Update longest sequence length if necessary
    
**Time Complexity:** O(n)
- Creating HashSet: O(n)
- Each number is only checked as potential sequence start once
- Each number is only visited once during sequence counting

**Space Complexity:** O(n)
- HashSet stores all unique numbers

## Implementation Notes
- Using HashSet eliminates need for sorting
- Only checking numbers that could be sequence starts prevents redundant work
- While loop efficiently counts sequence length
- Using Math.max() for cleaner longest length tracking