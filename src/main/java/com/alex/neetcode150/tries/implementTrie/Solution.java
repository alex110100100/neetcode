package com.alex.neetcode150.tries.implementTrie;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}

class PrefixTree {
    TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;

        for (Character c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }

        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;

        for (Character c : word.toCharArray()) {
            curr = curr.children.get(c);

            if (curr == null)
                return false;
        }

        return curr.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;

        for (Character c : prefix.toCharArray()) {
            curr = curr.children.get(c);

            if (curr == null)
                return false;
        }

        return true;
    }
}

class Test {
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 10;

        // Test case 1: LeetCode Example
        System.out.println("Test 1: LeetCode Example");
        PrefixTree trie1 = new PrefixTree();
        trie1.insert("apple");

        boolean search1a = trie1.search("apple");   // Expected: true
        boolean search1b = trie1.search("app");     // Expected: false
        boolean startsWith1a = trie1.startsWith("app"); // Expected: true

        trie1.insert("app");
        boolean search1c = trie1.search("app");     // Expected: true

        if (search1a == true && search1b == false && startsWith1a == true && search1c == true) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('apple'): expected true, got " + search1a);
            System.out.println("search('app') before insert: expected false, got " + search1b);
            System.out.println("startsWith('app'): expected true, got " + startsWith1a);
            System.out.println("search('app') after insert: expected true, got " + search1c);
        }
        System.out.println();

        // Test case 2: Empty string and single character
        System.out.println("Test 2: Empty string and single character");
        PrefixTree trie2 = new PrefixTree();
        trie2.insert("a");

        boolean search2a = trie2.search("a");       // Expected: true
        boolean search2b = trie2.search("");        // Expected: false (typically)
        boolean startsWith2a = trie2.startsWith("a"); // Expected: true
        boolean startsWith2b = trie2.startsWith(""); // Expected: true (empty prefix)

        if (search2a == true && search2b == false && startsWith2a == true && startsWith2b == true) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('a'): expected true, got " + search2a);
            System.out.println("search(''): expected false, got " + search2b);
            System.out.println("startsWith('a'): expected true, got " + startsWith2a);
            System.out.println("startsWith(''): expected true, got " + startsWith2b);
        }
        System.out.println();

        // Test case 3: Multiple words with common prefixes
        System.out.println("Test 3: Multiple words with common prefixes");
        PrefixTree trie3 = new PrefixTree();
        trie3.insert("cat");
        trie3.insert("car");
        trie3.insert("card");
        trie3.insert("care");

        boolean search3a = trie3.search("cat");     // Expected: true
        boolean search3b = trie3.search("car");     // Expected: true
        boolean search3c = trie3.search("ca");      // Expected: false
        boolean startsWith3a = trie3.startsWith("ca"); // Expected: true
        boolean startsWith3b = trie3.startsWith("car"); // Expected: true
        boolean startsWith3c = trie3.startsWith("card"); // Expected: true

        if (search3a && search3b && !search3c && startsWith3a && startsWith3b && startsWith3c) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('cat'): expected true, got " + search3a);
            System.out.println("search('car'): expected true, got " + search3b);
            System.out.println("search('ca'): expected false, got " + search3c);
            System.out.println("startsWith('ca'): expected true, got " + startsWith3a);
            System.out.println("startsWith('car'): expected true, got " + startsWith3b);
            System.out.println("startsWith('card'): expected true, got " + startsWith3c);
        }
        System.out.println();

        // Test case 4: Word is prefix of another word
        System.out.println("Test 4: Word is prefix of another word");
        PrefixTree trie4 = new PrefixTree();
        trie4.insert("hello");
        trie4.insert("hell");

        boolean search4a = trie4.search("hell");    // Expected: true
        boolean search4b = trie4.search("hello");   // Expected: true
        boolean search4c = trie4.search("he");      // Expected: false
        boolean startsWith4a = trie4.startsWith("he"); // Expected: true
        boolean startsWith4b = trie4.startsWith("hell"); // Expected: true

        if (search4a && search4b && !search4c && startsWith4a && startsWith4b) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('hell'): expected true, got " + search4a);
            System.out.println("search('hello'): expected true, got " + search4b);
            System.out.println("search('he'): expected false, got " + search4c);
            System.out.println("startsWith('he'): expected true, got " + startsWith4a);
            System.out.println("startsWith('hell'): expected true, got " + startsWith4b);
        }
        System.out.println();

        // Test case 5: Non-existent words and prefixes
        System.out.println("Test 5: Non-existent words and prefixes");
        PrefixTree trie5 = new PrefixTree();
        trie5.insert("dog");

        boolean search5a = trie5.search("cat");     // Expected: false
        boolean search5b = trie5.search("doggy");   // Expected: false
        boolean startsWith5a = trie5.startsWith("cat"); // Expected: false
        boolean startsWith5b = trie5.startsWith("doggy"); // Expected: false
        boolean startsWith5c = trie5.startsWith("d"); // Expected: true

        if (!search5a && !search5b && !startsWith5a && !startsWith5b && startsWith5c) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('cat'): expected false, got " + search5a);
            System.out.println("search('doggy'): expected false, got " + search5b);
            System.out.println("startsWith('cat'): expected false, got " + startsWith5a);
            System.out.println("startsWith('doggy'): expected false, got " + startsWith5b);
            System.out.println("startsWith('d'): expected true, got " + startsWith5c);
        }
        System.out.println();

        // Test case 6: Case sensitivity
        System.out.println("Test 6: Case sensitivity");
        PrefixTree trie6 = new PrefixTree();
        trie6.insert("Hello");

        boolean search6a = trie6.search("Hello");   // Expected: true
        boolean search6b = trie6.search("hello");   // Expected: false
        boolean startsWith6a = trie6.startsWith("Hel"); // Expected: true
        boolean startsWith6b = trie6.startsWith("hel"); // Expected: false

        if (search6a && !search6b && startsWith6a && !startsWith6b) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('Hello'): expected true, got " + search6a);
            System.out.println("search('hello'): expected false, got " + search6b);
            System.out.println("startsWith('Hel'): expected true, got " + startsWith6a);
            System.out.println("startsWith('hel'): expected false, got " + startsWith6b);
        }
        System.out.println();

        // Test case 7: Duplicate insertions
        System.out.println("Test 7: Duplicate insertions");
        PrefixTree trie7 = new PrefixTree();
        trie7.insert("test");
        trie7.insert("test"); // Insert same word again

        boolean search7 = trie7.search("test");     // Expected: true
        boolean startsWith7 = trie7.startsWith("tes"); // Expected: true

        if (search7 && startsWith7) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('test'): expected true, got " + search7);
            System.out.println("startsWith('tes'): expected true, got " + startsWith7);
        }
        System.out.println();

        // Test case 8: Long words
        System.out.println("Test 8: Long words");
        PrefixTree trie8 = new PrefixTree();
        String longWord = "supercalifragilisticexpialidocious";
        trie8.insert(longWord);

        boolean search8a = trie8.search(longWord);  // Expected: true
        boolean search8b = trie8.search("supercalifragilisticexpialidociou"); // Expected: false
        boolean startsWith8a = trie8.startsWith("super"); // Expected: true
        boolean startsWith8b = trie8.startsWith("supercalifragilisticexpialidocious"); // Expected: true

        if (search8a && !search8b && startsWith8a && startsWith8b) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search(longWord): expected true, got " + search8a);
            System.out.println("search(longWord-1): expected false, got " + search8b);
            System.out.println("startsWith('super'): expected true, got " + startsWith8a);
            System.out.println("startsWith(longWord): expected true, got " + startsWith8b);
        }
        System.out.println();

        // Test case 9: Sequential operations
        System.out.println("Test 9: Sequential operations");
        PrefixTree trie9 = new PrefixTree();

        // Start with empty trie
        boolean empty1 = trie9.search("any");       // Expected: false
        boolean empty2 = trie9.startsWith("a");     // Expected: false

        // Add first word
        trie9.insert("word");
        boolean after1a = trie9.search("word");     // Expected: true
        boolean after1b = trie9.search("wor");      // Expected: false
        boolean after1c = trie9.startsWith("wo");   // Expected: true

        // Add second word with common prefix
        trie9.insert("work");
        boolean after2a = trie9.search("work");     // Expected: true
        boolean after2b = trie9.search("word");     // Expected: true (still there)
        boolean after2c = trie9.startsWith("wor");  // Expected: true

        if (!empty1 && !empty2 && after1a && !after1b && after1c &&
                after2a && after2b && after2c) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Empty trie checks failed or sequential operations failed");
        }
        System.out.println();

        // Test case 10: Edge cases with single letters
        System.out.println("Test 10: Single letter words and prefixes");
        PrefixTree trie10 = new PrefixTree();
        trie10.insert("a");
        trie10.insert("b");
        trie10.insert("ab");

        boolean search10a = trie10.search("a");     // Expected: true
        boolean search10b = trie10.search("b");     // Expected: true
        boolean search10c = trie10.search("ab");    // Expected: true
        boolean search10d = trie10.search("ba");    // Expected: false
        boolean startsWith10a = trie10.startsWith("a"); // Expected: true
        boolean startsWith10b = trie10.startsWith("ab"); // Expected: true

        if (search10a && search10b && search10c && !search10d && startsWith10a && startsWith10b) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("search('a'): expected true, got " + search10a);
            System.out.println("search('b'): expected true, got " + search10b);
            System.out.println("search('ab'): expected true, got " + search10c);
            System.out.println("search('ba'): expected false, got " + search10d);
            System.out.println("startsWith('a'): expected true, got " + startsWith10a);
            System.out.println("startsWith('ab'): expected true, got " + startsWith10b);
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