package com.sean.leetcode.LeetCode3499;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-21 07:24
 * @Description: https://leetcode.cn/problems/maximize-active-section-with-trade-i
 * 3499. 操作后最大活跃区段数 I
 * 给你一个长度为 n 的二进制字符串 s，其中：
 * '1' 表示一个 活跃 区段。
 * '0' 表示一个 非活跃 区段。
 * 你可以执行 最多一次操作 来最大化 s 中的活跃区段数量。
 * 在一次操作中，你可以：
 * 将一个被 '0' 包围的连续 '1' 区块转换为全 '0'。
 * 然后，将一个被 '1' 包围的连续 '0' 区块转换为全 '1'。
 * 返回在执行最优操作后，s 中的 最大 活跃区段数。
 * 注意：处理时需要在 s 的两侧加上 '1' ，即 t = '1' + s + '1'。
 * 这些加上的 '1' 不会影响最终的计数。
 * 1 <= n == s.length <= 10^5
 * s[i] 仅包含 '0' 或 '1'
 */
public class Solution {

    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        int total1 = 0, max = 0, pre0 = Integer.MIN_VALUE, cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || str[i] != str[i + 1]) {
                if (str[i] == '1') {
                    total1 += cnt;
                } else {
                    max = Math.max(max, pre0 + cnt);
                    pre0 = cnt;
                }
                cnt = 0;
            }
        }
        return total1 + max;
    }

}
