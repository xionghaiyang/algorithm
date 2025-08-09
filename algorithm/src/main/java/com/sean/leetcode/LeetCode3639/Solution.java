package com.sean.leetcode.LeetCode3639;

/**
 * @Author xionghaiyang
 * @Date 2025-08-09 18:04
 * @Description https://leetcode.cn/problems/minimum-time-to-activate-string
 * 3639. 变为活跃状态的最小时间
 * 给你一个长度为 n 的字符串 s 和一个整数数组 order，其中 order 是范围 [0, n - 1] 内数字的一个 排列。
 * 从时间 t = 0 开始，在每个时间点，将字符串 s 中下标为 order[t] 的字符替换为 '*'。
 * 如果 子字符串 包含 至少 一个 '*' ，则认为该子字符串有效。
 * 如果字符串中 有效子字符串 的总数大于或等于 k，则称该字符串为 活跃 字符串。
 * 返回字符串 s 变为 活跃 状态的最小时间 t。
 * 如果无法变为活跃状态，返回 -1。
 * 1 <= n == s.length <= 10^5
 * order.length == n
 * 0 <= order[i] <= n - 1
 * s 由小写英文字母组成。
 * order 是从 0 到 n - 1 的整数排列。
 * 1 <= k <= 10^9
 */
public class Solution {

    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        if ((long) n * (n + 1) / 2 < k) {
            return -1;
        }
        int[] star = new int[n];
        int res = -1, left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(order, star, k, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean check(int[] order, int[] star, int k, int m) {
        m++;
        for (int i = 0; i < m; i++) {
            star[order[i]] = m;
        }
        int cnt = 0, last = -1;
        for (int i = 0; i < star.length; i++) {
            if (star[i] == m) {
                last = i;
            }
            cnt += last + 1;
            if (cnt >= k) {
                return true;
            }
        }
        return false;
    }

}
