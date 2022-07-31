package com.sean.leetcode;

public class LeetCode5 {

    public static String longestPalindrome1(String s) {
        if (s.length() < 2) {
            return s;
        }
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i; j < s.length(); j++) {
                //截取所有子串，然后逐个判断是否是回文的
                if (isPalindrome(s, i, j)) {
                    if (maxLen < j - i + 1) {
                        start = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    //判断是否是回文串
    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public static String longestPalindrome2(String s) {
        if (s.length() < 2) {
            return s;
        }
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i; j < s.length(); j++) {
                //截取所有的子串，如果截取的子串小于等于之前遍历过的最大回文子串，直接跳过。
                //因为截取的子串即使是回文串也不可能是最大的，所以不需要判断
                if (j - i < maxLen) {
                    continue;
                }
                if (isPalindrome(s, i, j)) {
                    if (maxLen < j - i + 1) {
                        start = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static String longestPalindrome3(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            i = process(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    private static int process(char[] str, int left, int[] range) {
        //将回文串看成从中间向两边扩散
        //先查找中间部分
        int right = left;
        while (right < str.length - 1 && str[right + 1] == str[left]) {
            right++;
        }
        //返回中间部分最后一个字符
        int res = right;
        //从中间向左右扩散
        while (left > 0 && right < str.length - 1 && str[left - 1] == str[right + 1]) {
            left--;
            right++;
        }
        //记录最大的长度
        if (right - left > range[1] - range[0]) {
            range[0] = left;
            range[1] = right;
        }
        return res;
    }

    public static String longestPalindrome4(String s) {
        //边界条件判断
        if (s.length() < 2) {
            return s;
        }
        //start表示最长回文串开始的位置
        //maxLen表示最长回文串的长度
        int start = 0, maxLen = 0;
        int length = s.length();
        for (int i = 0; i < length; ) {
            //如果剩余子串长度小于目前查找到的最长的回文子串的长度，直接终止循环
            //因为即使他是回文子串，也不是最长的，所以直接终止循环，不再判断
            if (length - i <= maxLen / 2) {
                break;
            }
            int left = i, right = i;
            while (right < length - 1 && s.charAt(right + 1) == s.charAt(right)) {
                //过滤掉重复的
                right++;
            }
            //下次再判断的时候从重复的下一个字符开始判断
            i = right + 1;
            //然后往两边判断，找到回文子串的长度
            while (right < length - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) {
                right++;
                left--;
            }
            //保留最长的
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        int maxLength = 1;
        int begin = 0;
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abaabd"));
    }

}
