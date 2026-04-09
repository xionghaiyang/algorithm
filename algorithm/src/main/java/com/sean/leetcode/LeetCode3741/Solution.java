package com.sean.leetcode.LeetCode3741;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-04-10 07:21
 * @Description https://leetcode.cn/problems/minimum-distance-between-three-equal-elements-ii
 * 3741. 三个相等元素之间的最小距离 II
 * 给你一个整数数组 nums。
 * 如果满足 nums[i] == nums[j] == nums[k]，且 (i, j, k) 是 3 个 不同 下标，那么三元组 (i, j, k) 被称为 有效三元组 。
 * 有效三元组 的 距离 被定义为 abs(i - j) + abs(j - k) + abs(k - i)，其中 abs(x) 表示 x 的 绝对值 。
 * 返回一个整数，表示 有效三元组 的 最小 可能距离。
 * 如果不存在 有效三元组 ，返回 -1。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= n
 */
public class Solution {

    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], e -> new ArrayList<>()).add(i);
        }
        int res = Integer.MAX_VALUE;
        for (List<Integer> list : map.values()) {
            for (int i = 2; i < list.size(); i++) {
                res = Math.min(res, (list.get(i) - list.get(i - 2)) * 2);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
