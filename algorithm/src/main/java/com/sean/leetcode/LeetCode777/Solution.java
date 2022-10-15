package com.sean.leetcode.LeetCode777;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-02 21:45
 * @Description: https://leetcode.cn/problems/swap-adjacent-in-lr-string/
 * 777. 在LR字符串中交换相邻字符
 * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。
 * 一次移动操作指用一个"XL"替换一个"LX"，或者用一个"RX"替换一个"XR"。
 * 现给定起始字符串start和结束字符串end，请编写代码，
 * 当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 */
public class Solution {

    public boolean canTransform(String start, String end) {
        int m = start.length();
        int n = end.length();
        if (m != n) {
            return false;
        }
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != end.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < m) {
            if (start.charAt(i) != 'X') {
                return false;
            }
            i++;
        }
        while (j < m) {
            if (end.charAt(j) != 'X') {
                return false;
            }
            j++;
        }
        return true;
    }

}
