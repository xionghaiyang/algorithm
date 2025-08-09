package com.sean.leetcode.LeetCode3638;

/**
 * @Author xionghaiyang
 * @Date 2025-08-09 17:06
 * @Description https://leetcode.cn/problems/maximum-balanced-shipments
 * 3638. 平衡装运的最大数量
 * 给你一个长度为 n 的整数数组 weight，表示按直线排列的 n 个包裹的重量。
 * 装运 定义为包裹的一个连续子数组。
 * 如果一个装运满足以下条件，则称其为 平衡装运：最后一个包裹的重量 严格小于 该装运中所有包裹中 最大重量 。
 * 选择若干个 不重叠 的连续平衡装运，并满足 每个包裹最多出现在一次装运中（部分包裹可以不被装运）。
 * 返回 可以形成的平衡装运的最大数量 。
 * 2 <= n <= 10^5
 * 1 <= weight[i] <= 10^9
 */
public class Solution {

    public int maxBalancedShipments(int[] weight) {
        int res = 0;
        int n = weight.length;
        for (int i = 1; i < n; i++) {
            if (weight[i - 1] > weight[i]) {
                res++;
                i++;
            }
        }
        return res;
    }

}
