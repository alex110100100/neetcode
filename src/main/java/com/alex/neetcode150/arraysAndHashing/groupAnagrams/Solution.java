package com.alex.neetcode150.arraysAndHashing.groupAnagrams;

import java.util.*;

// This one sucks, was just first attempt
class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            String s1 = strs[i];
            if (s1.equals("ZZZ")) continue;
            List<String> anagrams = new ArrayList<>();
            anagrams.add(s1);

            for (int j = i + 1; j < strs.length; j++) {
                String s2 = strs[j];
                if (s2.equals("ZZZ")) continue;
                if (isAnagram(s1, s2)) {
                    anagrams.add(s2);
                    strs[j] = "ZZZ";
                }
            }
            result.add(anagrams);
        }

        return result;
    }

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


class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> charCountsToAnagrams = new HashMap<>();

        for (String str : strs) {
            int[] charCounts = new int[26];

            for (char c : str.toCharArray()) {
                charCounts[c - 'a']++;
            }

            StringBuilder keyBuilder = new StringBuilder();
            for (int count : charCounts) {
                keyBuilder.append(count);
                keyBuilder.append('#'); // Use a delimiter between counts
            }
            String key = keyBuilder.toString();


            charCountsToAnagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(charCountsToAnagrams.values());
    }
}


class Test {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // Test case 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result1 = solution.groupAnagrams(strs1);
        System.out.println(result1);  // Output: [[eat, tea, ate], [tan, nat], [bat]]

        // Additional test cases
        String[] strs2 = {"a"};
        List<List<String>> result2 = solution.groupAnagrams(strs2);
        System.out.println(result2);  // Output: [[a]]

        String[] strs3 = {"abc", "cba"};
        List<List<String>> result3 = solution.groupAnagrams(strs3);
        System.out.println(result3);  // Output: [[abc, cba]]
    }
}






