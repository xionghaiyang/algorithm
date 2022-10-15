package com.sean.leetcode.LeetCode1790;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-11 08:13
 * @Description: https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
 * 1790. 仅执行一次字符串交换能否使两个字符串相等
 * 给你长度相等的两个字符串 s1 和 s2 。
 * 一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        int index1 = -1;
        int index2 = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 2) {
                    return false;
                }
                if (index1 == -1) {
                    index1 = i;
                } else if (index2 == -1) {
                    index2 = i;
                }
            }
        }
        return count == 0 || (count == 2 && s1.charAt(index1) == s2.charAt(index2) && s1.charAt(index2) == s2.charAt(index1));
    }

}
