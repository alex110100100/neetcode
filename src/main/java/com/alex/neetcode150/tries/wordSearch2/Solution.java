package com.alex.neetcode150.tries.wordSearch2;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word;
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;

        char c = board[i][j];
        if (c == '#' || !node.children.containsKey(c)) return;

        node = node.children.get(c);

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        board[i][j] = '#';

        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);

        board[i][j] = c;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                curr.children.putIfAbsent(c, new TrieNode());
                curr = curr.children.get(c);
            }
            curr.word = word;
        }

        return root;
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 8;

        // Test case 1: LeetCode Example 1
        System.out.println("Test 1: LeetCode Example 1");
        char[][] board1 = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words1 = {"oath","pea","eat","rain"};
        List<String> expected1 = Arrays.asList("eat", "oath");
        List<String> result1 = solution.findWords(board1, words1);

        if (listsEqualIgnoreOrder(result1, expected1)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected1);
            System.out.println("Got: " + result1);
        }
        System.out.println();

        // Test case 2: LeetCode Example 2
        System.out.println("Test 2: LeetCode Example 2");
        char[][] board2 = {
                {'a','b'},
                {'c','d'}
        };
        String[] words2 = {"abcb"};
        List<String> expected2 = Arrays.asList(); // No valid path for "abcb"
        List<String> result2 = solution.findWords(board2, words2);

        if (listsEqualIgnoreOrder(result2, expected2)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2);
            System.out.println("Got: " + result2);
        }
        System.out.println();

        // Test case 3: Single cell board
        System.out.println("Test 3: Single cell board");
        char[][] board3 = {{'a'}};
        String[] words3 = {"a", "aa", "b"};
        List<String> expected3 = Arrays.asList("a");
        List<String> result3 = solution.findWords(board3, words3);

        if (listsEqualIgnoreOrder(result3, expected3)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3);
            System.out.println("Got: " + result3);
        }
        System.out.println();

        // Test case 4: Words with common prefixes
        System.out.println("Test 4: Words with common prefixes");
        char[][] board4 = {
                {'c','a','t'},
                {'a','r','s'},
                {'t','s','e'}
        };
        String[] words4 = {"cat", "car", "cats", "care"};
        List<String> expected4 = Arrays.asList("cat", "car", "cats");
        List<String> result4 = solution.findWords(board4, words4);

        if (listsEqualIgnoreOrder(result4, expected4)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4);
            System.out.println("Got: " + result4);
        }
        System.out.println();

        // Test case 5: No words found
        System.out.println("Test 5: No words found");
        char[][] board5 = {
                {'a','b'},
                {'c','d'}
        };
        String[] words5 = {"xyz", "hello", "world"};
        List<String> expected5 = Arrays.asList();
        List<String> result5 = solution.findWords(board5, words5);

        if (listsEqualIgnoreOrder(result5, expected5)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5);
            System.out.println("Got: " + result5);
        }
        System.out.println();

        // Test case 6: All words found
        System.out.println("Test 6: All words can be found");
        char[][] board6 = {
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'}
        };
        String[] words6 = {"a", "ab", "abc", "be", "bed", "bef"};
        List<String> expected6 = Arrays.asList("a", "ab", "abc", "be", "bed", "bef");
        List<String> result6 = solution.findWords(board6, words6);

        if (listsEqualIgnoreOrder(result6, expected6)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6);
            System.out.println("Got: " + result6);
        }
        System.out.println();

        // Test case 7: Duplicate words in input
        System.out.println("Test 7: Duplicate words in input array");
        char[][] board7 = {
                {'a','b'},
                {'c','d'}
        };
        String[] words7 = {"ab", "ab", "ba", "ba"};
        List<String> expected7 = Arrays.asList("ab", "ba"); // Should return unique words only
        List<String> result7 = solution.findWords(board7, words7);

        if (listsEqualIgnoreOrder(result7, expected7)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7);
            System.out.println("Got: " + result7);
        }
        System.out.println();

        // Test case 8: Words that require backtracking
        System.out.println("Test 8: Words requiring backtracking");
        char[][] board9 = {
                {'a','a','a'},
                {'a','a','a'},
                {'a','a','a'}
        };
        String[] words8 = {"aaa", "aaaa", "aaaaa", "aaaaaa"};
        List<String> expected8 = Arrays.asList("aaa", "aaaa", "aaaaa", "aaaaaa");
        List<String> result8 = solution.findWords(board9, words8);

        if (listsEqualIgnoreOrder(result8, expected8)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8);
            System.out.println("Got: " + result8);
        }
        System.out.println();

        // Summary
        System.out.println("=== TEST SUMMARY ===");
        System.out.println("Tests passed: " + testsPassed + "/" + totalTests);
        if (testsPassed == totalTests) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }

    // Helper method to check if two lists contain the same elements (order doesn't matter)
    public static boolean listsEqualIgnoreOrder(List<String> a, List<String> b) {
        if (a.size() != b.size()) return false;

        Set<String> setA = new HashSet<>(a);
        Set<String> setB = new HashSet<>(b);

        return setA.equals(setB);
    }
}