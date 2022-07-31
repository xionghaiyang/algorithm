package com.sean.leetcode;

public class LeetCodeInterview0105 {

    public static boolean oneEditAway(String first, String second) {
        int m = first.length();
        int n = second.length();
        if (n - m == 1) {
            return oneInsert(first, second);
        } else if (m - n == 1) {
            return oneInsert(second, first);
        } else if (m == n) {
            boolean foundDifference = false;
            for (int i = 0; i < m; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (!foundDifference) {
                        foundDifference = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean oneInsert(String shorter, String longer) {
        int index1 = 0, index2 = 0;
        while (index1 < shorter.length() && index2 < longer.length()) {
            if (shorter.charAt(index1) == longer.charAt(index2)) {
                index1++;
            }
            index2++;
            if (index2 - index1 > 1) {
                return false;
            }
        }
        return true;
    }

}
