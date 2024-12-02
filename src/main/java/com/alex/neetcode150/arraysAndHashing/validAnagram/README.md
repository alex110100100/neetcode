# 242 - Valid Anagram

[Problem Link](https://leetcode.com/problems/valid-anagram/description/)

## Problem Statement

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Examples

### Example 1:
```python
Input: s = "anagram", t = "nagaram"
Output: true
```

### Example 2:
```python
Input: s = "rat", t = "car"
Output: false
```

## Solution Approaches

### 1. Sorting Approach
- Sort both strings alphabetically
- Check if sorted strings are equal
- Most space-efficient for languages other than Java (where Strings are immutable)
- Sorting algorithm options:
    - HeapSort: Space O(1)
    - QuickSort: Space O(log n)
- **Time Complexity:** O(n log n)
- **Space Complexity:** O(n)

### 2. HashMap/Counter Approach
- Create a HashMap (or array for ASCII values) to track character counts
- Iterate over both strings simultaneously
- For string 1: increment count for each character
- For string 2: decrement count for each character
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)