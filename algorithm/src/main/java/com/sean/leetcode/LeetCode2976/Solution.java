package com.sean.leetcode.LeetCode2976;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-01-29 11:59
 * @Description https://leetcode.cn/problems/minimum-cost-to-convert-string-i
 * 2976. 转换字符串的最小成本 I
 * 给你两个下标从 0 开始的字符串 source 和 target ，它们的长度均为 n 并且由 小写 英文字母组成。
 * 另给你两个下标从 0 开始的字符数组 original 和 changed ，以及一个整数数组 cost ，其中 cost[i] 代表将字符 original[i] 更改为字符 changed[i] 的成本。
 * 你从字符串 source 开始。
 * 在一次操作中，如果 存在 任意 下标 j 满足 cost[j] == z  、original[j] == x 以及 changed[j] == y 。
 * 你就可以选择字符串中的一个字符 x 并以 z 的成本将其更改为字符 y 。
 * 返回将字符串 source 转换为字符串 target 所需的 最小 成本。
 * 如果不可能完成转换，则返回 -1 。
 * 注意，可能存在下标 i 、j 使得 original[j] == original[i] 且 changed[j] == changed[i] 。
 * 1 <= source.length == target.length <= 10^5
 * source、target 均由小写英文字母组成
 * 1 <= cost.length== original.length == changed.length <= 2000
 * original[i]、changed[i] 是小写英文字母
 * 1 <= cost[i] <= 10^6
 * original[i] != changed[i]
 */
public class Solution {

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] dis = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dis[i], INF);
            dis[i][i] = 0;
        }
        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a', v = changed[i] - 'a', w = cost[i];
            dis[u][v] = Math.min(dis[u][v], w);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (dis[i][k] == INF) {
                    continue;
                }
                for (int j = 0; j < 26; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        long res = 0;
        for (int i = 0; i < source.length(); i++) {
            int d = dis[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
            if (d == INF) {
                return -1;
            }
            res += d;
        }
        return res;
    }

}
