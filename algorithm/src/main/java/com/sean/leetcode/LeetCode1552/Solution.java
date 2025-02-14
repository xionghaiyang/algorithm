package com.sean.leetcode.LeetCode1552;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-02-14 09:23
 * @Description https://leetcode.cn/problems/magnetic-force-between-two-balls/
 * 1552. 两球之间的磁力
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。
 * Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * 所有 position 中的整数 互不相同 。
 * 2 <= m <= position.length
 */
public class Solution {

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int left = 1, right = position[n - 1] - position[0], res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, position, m)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean check(int x, int[] position, int m) {
        int pre = position[0], cnt = 1;
        int n = position.length;
        for (int i = 1; i < n; i++) {
            if (position[i] - pre >= x) {
                pre = position[i];
                cnt++;
            }
        }
        return cnt >= m;
    }

}
