package com.alex.neetcode150.arraysAndHashing.validAnagram;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

// Using sorting
class Solution1 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        // sort both strings
        String s1 = s.chars().sorted()
                .collect(
                        StringBuilder::new,                  // Supplier
                        StringBuilder::appendCodePoint,      // Accumulator
                        StringBuilder::append                // Combiner
                )
                .toString();

        String s2 = t.chars().sorted()
                .collect(
                        StringBuilder::new,                  // Supplier
                        StringBuilder::appendCodePoint,      // Accumulator
                        StringBuilder::append                // Combiner
                )
                .toString();

        return s1.equals(s2);
    }
}

// Using a Hashmap
class Solution2 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            counts.compute(c1, (key, value) -> (value == null) ? 1 : value + 1);
            counts.compute(c2, (key, value) -> (value == null) ? -1 : value - 1);

            if (counts.get(c1) != null && counts.get(c1) == 0) counts.remove(c1);
            if (counts.get(c2) != null && counts.get(c2) == 0) counts.remove(c2);
        }

        return counts.isEmpty();
    }
}

//Using an array
class Solution3 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            counts[c1 - 'a']++;
            counts[c2 - 'a']--;
        }

        for (int count : counts) {
            if (count != 0) return false;
        }

        return true;
    }
}


class Test {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();

        // Test cases
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println(solution.isAnagram(s1, t1)); // Expected: true

        String s2 = "rat";
        String t2 = "car";
        System.out.println(solution.isAnagram(s2, t2)); // Expected: false

        String s3 = "listen";
        String t3 = "silent";
        System.out.println(solution.isAnagram(s3, t3)); // Expected: true
    }
}
