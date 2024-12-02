# 217 - Contains Duplicate

[Problem Link](https://leetcode.com/problems/contains-duplicate/description/)

## Problem Statement

Given an integer array nums, return true if any value appears at least twice in the array,
and return false if every element is distinct.

## Examples

### Example 1:
```python
Input: nums = [1,2,3,1]
Output: true
```

### Example 2:
```python
Input: nums = [1,2,3,4]
Output: false
```

### Example 3:
```python
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
```

## Solution Approaches

### 1. Nested Loop Comparison
- Compare each element with all elements after it
- First solution that comes to mind, but not efficient
- **Time Complexity:** O(n²)
- **Space Complexity:** O(1)

### 2. Sort and Compare
- Sort the elements first
- Iterate once and compare each element with its neighbor
- **Time Complexity:** O(n log n)
- **Space Complexity:** O(1)

### 3. HashSet Approach
- Iterate over the elements once
- Add elements to a hashset as you go
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)