package com.sean.leetcode.LeetCode3117;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-08-16 08:41
 * @Description https://leetcode.cn/problems/minimum-sum-of-values-by-dividing-array/
 * 3117. 划分数组得到最小的值之和
 * 给你两个数组 nums 和 andValues，长度分别为 n 和 m。
 * 数组的 值 等于该数组的 最后一个 元素。
 * 你需要将 nums 划分为 m 个 不相交的连续子数组 ，对于第 ith 个子数组 [li, ri]，
 * 子数组元素的按位 AND 运算结果等于 andValues[i]，换句话说，
 * 对所有的 1 <= i <= m，nums[li] & nums[li + 1] & ... & nums[ri] == andValues[i] ，其中 & 表示按位 AND 运算符。
 * 返回将 nums 划分为 m 个子数组所能得到的可能的 最小 子数组 值 之和。
 * 如果无法完成这样的划分，则返回 -1 。
 * 1 <= n == nums.length <= 10^4
 * 1 <= m == andValues.length <= min(n, 10)
 * 1 <= nums[i] < 10^5
 * 0 <= andValues[j] < 10^5
 */
public class Solution {

    private static final int INF = (1 << 20) - 1;
    private Map<Integer, Integer>[] map;

    public int minimumValueSum(int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;
        map = new HashMap[m * n];
        for (int i = 0; i < m * n; i++) {
            map[i] = new HashMap<>();
        }
        int res = dfs(0, 0, INF, nums, andValues);
        return res < INF ? res : -1;
    }

    private int dfs(int i, int j, int cur, int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;
        int key = i * m + j;
        if (i == n && j == m) {
            return 0;
        }
        if (i == n || j == m) {
            return INF;
        }
        if (map[key].containsKey(cur)) {
            return map[key].get(cur);
        }
        cur &= nums[i];
        if ((cur & andValues[j]) < andValues[j]) {
            return INF;
        }
        int res = dfs(i + 1, j, cur, nums, andValues);
        if (cur == andValues[j]) {
            res = Math.min(res, dfs(i + 1, j + 1, INF, nums, andValues) + nums[i]);
        }
        map[key].put(cur, res);
        return res;
    }

}
