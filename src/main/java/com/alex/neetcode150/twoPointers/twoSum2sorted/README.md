# 167. Two Sum II - Input Array Is Sorted
[Problem Link](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/)

## Problem Statement
Given a 1-indexed array of integers `numbers` that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be `numbers[index1]` and `numbers[index2]` where `1 <= index1 < index2 < numbers.length`.

Return the indices of the two numbers, `index1` and `index2`, added by one as an integer array `[index1, index2]` of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element twice.

Your solution must use only constant extra space.

## Examples
### Example 1:
```python
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
```

### Example 2:
```python
Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
```

### Example 3:
```python
Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
```

## Solution Approach
### Two-Pointer Strategy
Use two pointers moving inward from opposite ends:
- Create pointer at start and end of array
- Calculate sum of numbers at both pointers
- If sum > target: move right pointer inward
- If sum < target: move left pointer inward
- Continue until target is found

### Example Walkthrough
Using `numbers = [2,7,11,15], target = 18`:
1. Start with pointers at `[2,7,11,15]`
2. Calculate sums and adjust pointers accordingly
3. Result will be `[2,3]` (representing indices 2 and 3)

### Time Complexity: O(n)
- Single pass through the array
- Each element examined at most once

### Space Complexity: O(1)
- Only using two pointers
- No extra space needed