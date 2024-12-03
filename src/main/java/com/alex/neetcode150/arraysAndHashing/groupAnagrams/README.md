# 49 - Group Anagrams

[Problem Link](https://leetcode.com/problems/group-anagrams/description/)

## Problem Statement

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Example

### Example 1:
```python
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

## Solution Approach

The solution involves two key considerations:
1. How to determine whether two strings are anagrams
2. How to group multiple anagrams together

### Implementation Details
- For each string, create a character frequency array
- Convert this array to a string to use as HashMap key
    - Important: We convert to string because array comparisons would compare memory addresses rather than values
- Store the original strings as values in the HashMap, grouped by their frequency key
- Use `Map.computeIfAbsent()` for cleaner implementation

### Key Points
- Each frequency array serves as a unique identifier for a group of anagrams
- All strings with the same character frequencies will be grouped together
- The HashMap automatically handles the grouping for us

### Complexity Analysis
- **Time Complexity:** O(m * n)
    - m = number of strings
    - n = average length of each string
    - We iterate over each character of each string once
- **Space Complexity:** O(m * n)
    - We store all strings in our HashMap
    - The character frequency arrays also contribute to space complexity