package com.sean.leetcode.LeetCode779;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-20 08:17
 * @Description: https://leetcode.cn/problems/k-th-symbol-in-grammar/
 * 779. 第K个语法符号
 * 我们构建了一个包含 n 行( 索引从 1  开始 )的表。
 * 首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 */
public class Solution {

    public int kthGrammar(int n, int k) {
        k--;
        int res = 0;
        while (k > 0) {
            k &= k - 1;
            res ^= 1;
        }
        return res;
    }

}
