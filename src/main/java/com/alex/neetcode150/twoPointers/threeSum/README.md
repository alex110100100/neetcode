# 15. 3Sum
[Problem Link](https://leetcode.com/problems/3sum/description/)

## Problem Statement
Given an integer array nums, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`.

Notice that the solution set must not contain duplicate triplets.

## Examples
### Example 1:
```python
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0
The distinct triplets are [-1,0,1] and [-1,-1,2].
Note: The order of the output and the order of the triplets does not matter.
```

### Example 2:
```python
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
```

### Example 3:
```python
Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0. Notice that the same number (0) can appear multiple times in a triplet.
```

## Solution Approach
### Building on Two Sum II
The solution leverages the approach used in Two Sum II (sorted array with two pointers):
1. **First, sort the array** (crucial step!)
2. Iterate through each number in the array
3. For each number:
    - Use two pointers (left and right) to find pairs that sum to its negative (i.e. such that num1 + num2 + num3 = 0)
    - Move pointers inward based on sum comparison

### Handling Duplicates
Note: Individual triplets CAN contain duplicate numbers (like `[-1,-1,2]` or `[0,0,0]`), but we can't have duplicate triplets in our result set.

Using a HashSet for results automatically handles this:
```java
Set<List<Integer>> results = new HashSet<>();
// When we add Arrays.asList(num1, num2, num3), HashSet ensures:
// [-1,-1,2] can appear in results
// but we won't get [-1,-1,2] twice in our final answer
```

### Time Complexity: O(n²)
- Sorting: O(n log n)
- Main loop: O(n)
- Two-pointer technique for each iteration: O(n)
- Overall: O(n²)

### Space Complexity: O(1)
- Not counting the space needed for output
- Only using pointers and variables for computation

### Implementation Notes
- Using a HashSet automatically handles duplicate triplets while allowing duplicate numbers within each triplet
- The array must be sorted first for the two-pointer approach to work