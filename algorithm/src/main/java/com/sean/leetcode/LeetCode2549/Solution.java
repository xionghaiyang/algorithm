package com.sean.leetcode.LeetCode2549;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-23 19:27
 * @Description: https://leetcode.cn/problems/count-distinct-numbers-on-board/
 * 2549. 统计桌面上的不同数字
 * 给你一个正整数 n ，开始时，它放在桌面上。在 10^9 天内，每天都要执行下述步骤：
 * 对于出现在桌面上的每个数字 x ，找出符合 1 <= i <= n 且满足 x % i == 1 的所有数字 i 。
 * 然后，将这些数字放在桌面上。
 * 返回在 10^9 天之后，出现在桌面上的 不同 整数的数目。
 * 注意：
 * 一旦数字放在桌面上，则会一直保留直到结束。
 * % 表示取余运算。例如，14 % 3 等于 2 。
 */
public class Solution {

    public int distinctIntegers(int n) {
        return n == 1 ? 1 : n - 1;
    }

}
