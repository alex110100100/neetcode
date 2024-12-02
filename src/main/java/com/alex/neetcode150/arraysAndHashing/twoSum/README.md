# 1 - Two Sum

[Problem Link](https://leetcode.com/problems/two-sum/description/)

## Problem Statement

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Examples

### Example 1:
```python
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1]
```

### Example 2:
```python
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

### Example 3:
```python
Input: nums = [3,3], target = 6
Output: [0,1]
```

## Solution Approaches

### 1. Brute Force Approach
- At each element of the array, iterate over each element after it
- Check if any pair sums to the target
- **Time Complexity:** O(n²)
- **Space Complexity:** O(1)

### 2. HashMap Approach (2-pass)
- Create a HashMap
- First pass: Add each element as key, with its index as value
- Second pass: Check if (target - currentElement) exists in HashMap
- Return the indices if found
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

### 3. HashMap Approach (1-pass)
- Similar to 2-pass but more efficient
- Store differences instead of values in HashMap
- At each element, check if it exists in HashMap
- Only requires a single iteration
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)