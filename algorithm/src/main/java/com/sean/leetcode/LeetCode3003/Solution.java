package com.sean.leetcode.LeetCode3003;

/**
 * @Author xionghaiyang
 * @Date 2025-10-17 12:19
 * @Description https://leetcode.cn/problems/maximize-the-number-of-partitions-after-operations
 * 3003. 执行操作后的最大分割数量
 * 给你一个下标从 0 开始的字符串 s 和一个整数 k。
 * 你需要执行以下分割操作，直到字符串 s 变为 空：
 * 选择 s 的最长 前缀，该前缀最多包含 k 个 不同 字符。
 * 删除 这个前缀，并将分割数量加一。
 * 如果有剩余字符，它们在 s 中保持原来的顺序。
 * 执行操作之 前 ，你可以将 s 中 至多一处 下标的对应字符更改为另一个小写英文字母。
 * 在最优选择情形下改变至多一处下标对应字符后，用整数表示并返回操作结束时得到的 最大 分割数量。
 * 1 <= s.length <= 10^4
 * s 只包含小写英文字母。
 * 1 <= k <= 26
 */
public class Solution {

    private int seg = 1;
    private int mask = 0;
    private int size = 0;

    public int maxPartitionsAfterOperations(String s, int k) {
        if (k == 26) {
            return 1;
        }
        int n = s.length();
        char[] str = s.toCharArray();
        int[] sufSeg = new int[n + 1];
        int[] sufMask = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            process(str[i], k);
            sufSeg[i] = seg;
            sufMask[i] = mask;
        }
        int res = seg;
        seg = 1;
        mask = 0;
        size = 0;
        for (int i = 0; i < n; i++) {
            int ans = seg + sufSeg[i + 1];
            int unionSize = Integer.bitCount(mask | sufMask[i + 1]);
            if (unionSize < k) {
                ans--;
            } else if (unionSize < 26 && size == k && Integer.bitCount(sufMask[i + 1]) == k) {
                ans++;
            }
            res = Math.max(res, ans);
            process(str[i], k);
        }
        return res;
    }

    private void process(char c, int k) {
        int bit = 1 << (c - 'a');
        if ((mask & bit) != 0) {
            return;
        }
        if (++size > k) {
            seg++;
            mask = bit;
            size = 1;
        } else {
            mask |= bit;
        }
    }

}
