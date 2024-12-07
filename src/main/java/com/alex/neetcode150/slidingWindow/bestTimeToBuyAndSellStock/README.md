# 121 - Best Time to Buy and Sell Stock

[Problem Link](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

## Problem Statement

You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

### Constraints
- Must buy before selling
- Can only perform one transaction
- Return 0 if no profit possible

## Examples

### Example 1:
```python
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: 
- Buy on day 2 (price = 1)
- Sell on day 5 (price = 6)
- Profit = 6 - 1 = 5
```

### Example 2:
```python
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: Prices only decrease, no profit possible
```

## Solution Approach

### Key Insight
- At each point, the maximum profit possible is the difference between:
    - Current price
    - Minimum price seen so far

### Implementation Strategy
1. Track two variables:
    - minPrice: lowest price seen so far
    - maxProfit: highest profit possible so far

2. For each price:
    - Update minPrice if current price is lower
    - Calculate potential profit (current price - minPrice)
    - Update maxProfit if potential profit is higher

**Time Complexity:** O(n)
- Single pass through array

**Space Complexity:** O(1)
- Only storing two variables

## Implementation Notes
- No need to consider previous prices once we have minPrice
- Works even with declining prices (returns 0)
- Automatically handles requirement that buy must come before sell
- Similar to Kadane's algorithm pattern

Would you like me to add or modify anything in these notes?