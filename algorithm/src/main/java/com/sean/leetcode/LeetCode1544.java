package com.sean.leetcode;

/**
 * 整理字符串
 * https://leetcode-cn.com/problems/make-the-string-great/
 */
public class LeetCode1544 {

    public static String makeGood(String s) {
        StringBuffer res = new StringBuffer();
        int resIndex = -1;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (res.length() > 0 && Character.toLowerCase(res.charAt(resIndex)) == Character.toLowerCase(ch) && res.charAt(resIndex) != ch) {
                res.deleteCharAt(resIndex);
                resIndex--;
            } else {
                res.append(ch);
                resIndex++;
            }
        }
        return res.toString();
    }

}
