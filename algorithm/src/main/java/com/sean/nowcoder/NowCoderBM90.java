package com.sean.nowcoder;

public class NowCoderBM90 {

    public static boolean check(int[] hash) {
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] < 0) {
                return false;
            }
        }
        return true;
    }

    public static String minWindow(String s, String t) {
        int cnt = s.length() + 1;
        int[] hash = new int[128];
        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i)] -= 1;
        }
        int slow = 0, fast = 0;
        int left = -1, right = -1;
        while (fast < s.length()) {
            char c = s.charAt(fast);
            hash[c]++;
            while (check(hash)) {
                if (cnt > fast - slow + 1) {
                    cnt = fast - slow + 1;
                    left = slow;
                    right = fast;
                }
                c = s.charAt(slow);
                hash[c]--;
                slow++;
            }
            fast++;
        }
        if (left == -1) {
            return "";
        }
        return s.substring(left, right + 1);
    }

}
