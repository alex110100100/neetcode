package com.alex.neetcode150.slidingWindow.bestTimeToBuyAndSellStock;

class Solution {
    public int maxProfit(int[] prices) {
        int currentPrice;
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int day = 1; day < prices.length; day++) {
            currentPrice = prices[day];
            minPrice = Math.min(minPrice, currentPrice);
            maxProfit = Math.max(maxProfit, currentPrice - minPrice);
        }

        return maxProfit;
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int expected1 = 5;
        int result1 = solution.maxProfit(prices1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with single price
        int[] prices2 = {5};
        int expected2 = 0;
        int result2 = solution.maxProfit(prices2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with two prices (ascending order)
        int[] prices3 = {3, 7};
        int expected3 = 4;
        int result3 = solution.maxProfit(prices3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 4: Edge case with two prices (descending order)
        int[] prices4 = {7, 3};
        int expected4 = 0;
        int result4 = solution.maxProfit(prices4);
        System.out.println(result4 == expected4 ? "Test Case 4 Passed" : "Test Case 4 Failed");
    }
}


