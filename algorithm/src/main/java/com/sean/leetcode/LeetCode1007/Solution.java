package com.sean.leetcode.LeetCode1007;

/**
 * @Author xionghaiyang
 * @Date 2025-05-03 05:50
 * @Description https://leetcode.cn/problems/minimum-domino-rotations-for-equal-row
 * 1007. 行相等的最少多米诺旋转
 * 在一排多米诺骨牌中，tops[i] 和 bottoms[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。
 * （一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）
 * 我们可以旋转第 i 张多米诺，使得 tops[i] 和 bottoms[i] 的值交换。
 * 返回能使 tops 中所有值或者 bottoms 中所有值都相同的最小旋转次数。
 * 如果无法做到，返回 -1.
 * 2 <= tops.length <= 2 * 10^4
 * bottoms.length == tops.length
 * 1 <= tops[i], bottoms[i] <= 6
 */
public class Solution {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length;
        int[][] cnt = new int[7][3];
        for (int i = 0; i < n; i++) {
            if (tops[i] == bottoms[i]) {
                cnt[tops[i]][2]++;
            } else {
                cnt[tops[i]][0]++;
                cnt[bottoms[i]][1]++;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            if (cnt[i][0] + cnt[i][1] + cnt[i][2] == n) {
                res = Math.min(res, Math.min(cnt[i][0], cnt[i][1]));
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
