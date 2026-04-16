package com.sean.leetcode.LeetCode3761;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-04-17 06:40
 * @Description https://leetcode.cn/problems/minimum-absolute-distance-between-mirror-pairs
 * 3761. 镜像对之间最小绝对距离
 * 给你一个整数数组 nums。
 * 镜像对 是指一对满足下述条件的下标 (i, j)：
 * 0 <= i < j < nums.length，并且
 * reverse(nums[i]) == nums[j]，其中 reverse(x) 表示将整数 x 的数字反转后形成的整数。
 * 反转后会忽略前导零，例如 reverse(120) = 21。
 * 返回任意镜像对的下标之间的 最小绝对距离。
 * 下标 i 和 j 之间的绝对距离为 abs(i - j)。
 * 如果不存在镜像对，返回 -1。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                res = Math.min(res, i - map.get(nums[i]));
            }
            map.put(reverse(nums[i]), i);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }

    private int reverse(int num) {
        int res = 0;
        while (num != 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }

}
