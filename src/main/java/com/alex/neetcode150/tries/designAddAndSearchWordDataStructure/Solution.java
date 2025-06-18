package com.alex.neetcode150.tries.designAddAndSearchWordDataStructure;

import java.util.*;

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean endOfWord = false;
}

// Solution 1 - most intuitive (to me, at least). But creates a new WordDictionary and String object on each recursion.
class WordDictionary1 {
    private TrieNode root;

    public WordDictionary1() {
        root = new TrieNode();
    }

    public WordDictionary1(TrieNode root) {
        this.root = root;
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);

            if (c == '.') {
                for (TrieNode node : curr.children.values()) {
                    WordDictionary1 wd = new WordDictionary1(node);

                    if (wd.search(word.substring(i + 1)))
                        return true;
                }
                return false;
            }

            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.endOfWord;
    }
}


// Solution 2 - using a helper method for the search saves us from creating extra WordDictionary and String objects.
class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }

    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    public boolean searchHelper(String word, TrieNode node, int index) {
        // base case
        if (index == word.length())
            return node.endOfWord;

        Character c = word.charAt(index);

        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                if (searchHelper(word, child, index + 1))
                    return true;
            }
            return false;
        }

        if (!node.children.containsKey(c))
            return false;

        node = node.children.get(c);
        return searchHelper(word, node, index + 1);
    }
}

class Test {
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 12;

        // Test case 1: LeetCode Example
        System.out.println("Test 1: LeetCode Example");
        WordDictionary wd1 = new WordDictionary();
        wd1.addWord("bad");
        wd1.addWord("dad");
        wd1.addWord("mad");

        boolean search1a = wd1.search("pad");   // Expected: false
        boolean search1b = wd1.search("bad");   // Expected: true
        boolean search1c = wd1.search(".ad");   // Expected: true (matches bad, dad, mad)
        boolean search1d = wd1.search("b..");   // Expected: true (matches bad)

        if (!search1a && search1b && search1c && search1d) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('pad'): expected false, got " + search1a);
            System.out.println("search('bad'): expected true, got " + search1b);
            System.out.println("search('.ad'): expected true, got " + search1c);
            System.out.println("search('b..'): expected true, got " + search1d);
        }
        System.out.println();

        // Test case 2: Single character and dot
        System.out.println("Test 2: Single character and dot");
        WordDictionary wd2 = new WordDictionary();
        wd2.addWord("a");
        wd2.addWord("b");

        boolean search2a = wd2.search("a");     // Expected: true
        boolean search2b = wd2.search(".");     // Expected: true (matches a or b)
        boolean search2c = wd2.search("c");     // Expected: false
        boolean search2d = wd2.search("..");    // Expected: false (no 2-char words)

        if (search2a && search2b && !search2c && !search2d) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('a'): expected true, got " + search2a);
            System.out.println("search('.'): expected true, got " + search2b);
            System.out.println("search('c'): expected false, got " + search2c);
            System.out.println("search('..'): expected false, got " + search2d);
        }
        System.out.println();

        // Test case 3: All dots
        System.out.println("Test 3: All dots pattern");
        WordDictionary wd3 = new WordDictionary();
        wd3.addWord("cat");
        wd3.addWord("dog");
        wd3.addWord("rat");

        boolean search3a = wd3.search("...");   // Expected: true (matches cat, dog, rat)
        boolean search3b = wd3.search("....");  // Expected: false (no 4-char words)
        boolean search3c = wd3.search("..");    // Expected: false (no 2-char words)

        if (search3a && !search3b && !search3c) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('...'): expected true, got " + search3a);
            System.out.println("search('....'): expected false, got " + search3b);
            System.out.println("search('..'): expected false, got " + search3c);
        }
        System.out.println();

        // Test case 4: Mixed dots and characters
        System.out.println("Test 4: Mixed dots and characters");
        WordDictionary wd4 = new WordDictionary();
        wd4.addWord("hello");
        wd4.addWord("world");

        boolean search4a = wd4.search("h.llo"); // Expected: true (matches hello)
        boolean search4b = wd4.search("w.rld"); // Expected: true (matches world)
        boolean search4c = wd4.search("h.ll.");  // Expected: true (matches hello)
        boolean search4d = wd4.search("w.r.d"); // Expected: true (matches world)
        boolean search4e = wd4.search("h.rld"); // Expected: false (doesn't match hello)
        boolean search4f = wd4.search("w.llo"); // Expected: false (doesn't match world)

        if (search4a && search4b && search4c && search4d && !search4e && !search4f) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('h.llo'): expected true, got " + search4a);
            System.out.println("search('w.rld'): expected true, got " + search4b);
            System.out.println("search('h.ll.'): expected true, got " + search4c);
            System.out.println("search('w.r.d'): expected true, got " + search4d);
            System.out.println("search('h.rld'): expected false, got " + search4e);
            System.out.println("search('w.llo'): expected false, got " + search4f);
        }
        System.out.println();

        // Test case 5: Empty dictionary
        System.out.println("Test 5: Empty dictionary");
        WordDictionary wd5 = new WordDictionary();

        boolean search5a = wd5.search("anything"); // Expected: false
        boolean search5b = wd5.search(".");        // Expected: false
        boolean search5c = wd5.search("");         // Expected: false

        if (!search5a && !search5b && !search5c) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('anything'): expected false, got " + search5a);
            System.out.println("search('.'): expected false, got " + search5b);
            System.out.println("search(''): expected false, got " + search5c);
        }
        System.out.println();

        // Test case 6: Words of different lengths
        System.out.println("Test 6: Words of different lengths");
        WordDictionary wd6 = new WordDictionary();
        wd6.addWord("a");
        wd6.addWord("ab");
        wd6.addWord("abc");
        wd6.addWord("abcd");

        boolean search6a = wd6.search(".");     // Expected: true (matches "a")
        boolean search6b = wd6.search("..");    // Expected: true (matches "ab")
        boolean search6c = wd6.search("...");   // Expected: true (matches "abc")
        boolean search6d = wd6.search("....");  // Expected: true (matches "abcd")
        boolean search6e = wd6.search(".....");// Expected: false (no 5-char words)
        boolean search6f = wd6.search("a...");  // Expected: true (matches "abcd")
        boolean search6g = wd6.search("ab..");  // Expected: true (matches "abcd")

        if (search6a && search6b && search6c && search6d && !search6e && search6f && search6g) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Some searches failed for words of different lengths");
        }
        System.out.println();

        // Test case 7: Case sensitivity
        System.out.println("Test 7: Case sensitivity");
        WordDictionary wd7 = new WordDictionary();
        wd7.addWord("Hello");
        wd7.addWord("WORLD");

        boolean search7a = wd7.search("Hello"); // Expected: true
        boolean search7b = wd7.search("hello"); // Expected: false
        boolean search7c = wd7.search("H.llo"); // Expected: true
        boolean search7d = wd7.search("h.llo"); // Expected: false
        boolean search7e = wd7.search("WORLD"); // Expected: true
        boolean search7f = wd7.search("W.RLD"); // Expected: true

        if (search7a && !search7b && search7c && !search7d && search7e && search7f) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Case sensitivity tests failed");
        }
        System.out.println();

        // Test case 8: Duplicate words
        System.out.println("Test 8: Duplicate word additions");
        WordDictionary wd8 = new WordDictionary();
        wd8.addWord("test");
        wd8.addWord("test"); // Add same word again
        wd8.addWord("best");

        boolean search8a = wd8.search("test");  // Expected: true
        boolean search8b = wd8.search(".est");  // Expected: true (matches test, best)
        boolean search8c = wd8.search("t.st");  // Expected: true (matches test)
        boolean search8d = wd8.search("rest");  // Expected: false

        if (search8a && search8b && search8c && !search8d) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Duplicate word tests failed");
        }
        System.out.println();

        // Test case 9: Long words with dots
        System.out.println("Test 9: Long words with dots");
        WordDictionary wd9 = new WordDictionary();
        wd9.addWord("programming");
        wd9.addWord("algorithm");

        boolean search9a = wd9.search("programming"); // Expected: true
        boolean search9b = wd9.search("prog.amming"); // Expected: true
        boolean search9c = wd9.search("...gramming"); // Expected: true
        boolean search9d = wd9.search("algorithm");   // Expected: true
        boolean search9e = wd9.search("algo.ithm");   // Expected: true
        boolean search9f = wd9.search(".........");   // Expected: true (matches algorithm - 9 chars)
        boolean search9g = wd9.search("...........");  // Expected: true (matches programming - 11 chars)
        boolean search9h = wd9.search("............"); // Expected: false (no 12-char words)

        if (search9a && search9b && search9c && search9d && search9e && search9f && search9g && !search9h) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Long word tests failed");
        }
        System.out.println();

        // Test case 10: Edge case - dots at boundaries
        System.out.println("Test 10: Dots at word boundaries");
        WordDictionary wd10 = new WordDictionary();
        wd10.addWord("word");

        boolean search10a = wd10.search(".ord"); // Expected: true
        boolean search10b = wd10.search("wor."); // Expected: true
        boolean search10c = wd10.search(".or."); // Expected: true
        boolean search10d = wd10.search("..rd"); // Expected: true
        boolean search10e = wd10.search("wo.."); // Expected: true

        if (search10a && search10b && search10c && search10d && search10e) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Boundary dot tests failed");
        }
        System.out.println();

        // Test case 11: Multiple possible matches
        System.out.println("Test 11: Multiple possible matches");
        WordDictionary wd11 = new WordDictionary();
        wd11.addWord("cat");
        wd11.addWord("bat");
        wd11.addWord("rat");
        wd11.addWord("hat");

        boolean search11a = wd11.search(".at"); // Expected: true (matches all: cat, bat, rat, hat)
        boolean search11b = wd11.search("c.t"); // Expected: true (matches cat)
        boolean search11c = wd11.search("..t"); // Expected: true (matches all)
        boolean search11d = wd11.search(".a."); // Expected: true (matches all)
        boolean search11e = wd11.search("mat"); // Expected: false (mat not added)

        if (search11a && search11b && search11c && search11d && !search11e) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Multiple match tests failed");
        }
        System.out.println();

        // Test case 12: Sequential operations
        System.out.println("Test 12: Sequential add and search operations");
        WordDictionary wd12 = new WordDictionary();

        // Initially empty
        boolean initial = wd12.search("any");   // Expected: false

        // Add first word
        wd12.addWord("first");
        boolean after1a = wd12.search("first"); // Expected: true
        boolean after1b = wd12.search("f...t"); // Expected: true
        boolean after1c = wd12.search("second");// Expected: false

        // Add second word
        wd12.addWord("second");
        boolean after2a = wd12.search("first"); // Expected: true (still there)
        boolean after2b = wd12.search("second");// Expected: true
        boolean after2c = wd12.search("......");// Expected: true (matches second - 6 chars)
        boolean after2d = wd12.search("....." ); // Expected: true (matches first - 5 chars)

        if (!initial && after1a && after1b && !after1c &&
                after2a && after2b && after2c && after2d) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Sequential operations failed");
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
}