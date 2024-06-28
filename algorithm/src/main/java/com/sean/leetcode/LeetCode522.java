package com.sean.leetcode;

public class LeetCode522 {

    public static int findLUSlength(String[] strs) {
        int n = strs.length;
        int res = -1;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (i != j && isSubseq(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res = Math.max(res, strs[i].length());
            }
        }
        return res;
    }

    public static boolean isSubseq(String s, String t) {
        int ps = 0, pt = 0;
        while (ps < s.length() && pt < t.length()) {
            if (s.charAt(ps) == t.charAt(pt)) {
                ps++;
            }
            pt++;
        }
        return ps == s.length();
    }

}
