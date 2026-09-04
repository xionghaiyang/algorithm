package com.sean.leetcode.LeetCode38;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-04 09:54
 * @Description: https://leetcode.cn/problems/count-and-say
 * 38. 外观数列
 * 「外观数列」是一个数位字符串序列，由递归公式定义：
 * countAndSay(1) = "1"
 * countAndSay(n) 是 countAndSay(n-1) 的行程长度编码。
 * 行程长度编码（RLE）是一种字符串压缩方法，其工作原理是通过将每个最大连续相同字符组替换为该组的长度后加上该字符本身。
 * 例如，要压缩字符串 "3322251" ，我们将 "33" 用 "23" 替换，将 "222" 用 "32" 替换，将 "5" 用 "15" 替换并将 "1" 用 "11" 替换。
 * 因此压缩后字符串变为 "23321511"。
 * 给定一个整数 n ，返回 外观数列 的第 n 个元素。
 * 1 <= n <= 30
 */
public class Solution {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        char[] str = countAndSay(n - 1).toCharArray();
        int m = str.length;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int cnt = 1;
            while (i + 1 < m && str[i + 1] == str[i]) {
                cnt++;
                i++;
            }
            res.append(cnt);
            res.append(str[i]);
        }
        return res.toString();
    }

}
