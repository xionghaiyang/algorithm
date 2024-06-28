package com.sean.leetcode;

/**
 * 外观数列
 * https://leetcode-cn.com/problems/count-and-say/
 */
public class LeetCode38 {

    public static String countAndSay(int n) {
        if (n < 1) {
            return null;
        }
        if (n == 1) {
            return "1";
        }
        char[] charArray = countAndSay(n - 1).toCharArray();
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            int count = 1;
            while (i + 1 < charArray.length && charArray[i + 1] == charArray[i]) {
                count++;
                i++;
            }
            res.append(count);
            res.append(charArray[i]);
        }
        return res.toString();
    }

}
