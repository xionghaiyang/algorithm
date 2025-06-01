package com.sean.leetcode.LeetCode455;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-01 10:18
 * @Description https://leetcode.cn/problems/assign-cookies
 * 455. 分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。
 * 但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j] 。
 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是满足尽可能多的孩子，并输出这个最大数值。
 * 1 <= g.length <= 3 * 10^4
 * 0 <= s.length <= 3 * 10^4
 * 1 <= g[i], s[j] <= 2^31 - 1
 */
public class Solution {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int n = g.length, m = s.length;
        int res = 0;
        for (int i = n - 1, j = m - 1; i >= 0; i--) {
            if (j >= 0 && s[j] >= g[i]) {
                res++;
                j--;
            }
        }
        return res;
    }

}
