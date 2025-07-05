package com.sean.leetcode.LeetCode1865;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-07-06 06:32
 * @Description https://leetcode.cn/problems/finding-pairs-with-a-certain-sum
 * 1865. 找出和为指定值的下标对
 * 给你两个整数数组 nums1 和 nums2 ，请你实现一个支持下述两类查询的数据结构：
 * 累加 ，将一个正整数加到 nums2 中指定下标对应元素上。
 * 计数 ，统计满足 nums1[i] + nums2[j] 等于指定值的下标对 (i, j) 数目（0 <= i < nums1.length 且 0 <= j < nums2.length）。
 * 实现 FindSumPairs 类：
 * FindSumPairs(int[] nums1, int[] nums2) 使用整数数组 nums1 和 nums2 初始化 FindSumPairs 对象。
 * void add(int index, int val) 将 val 加到 nums2[index] 上，即，执行 nums2[index] += val 。
 * int count(int tot) 返回满足 nums1[i] + nums2[j] == tot 的下标对 (i, j) 数目。
 * 1 <= nums1.length <= 1000
 * 1 <= nums2.length <= 10^5
 * 1 <= nums1[i] <= 10^9
 * 1 <= nums2[i] <= 10^5
 * 0 <= index < nums2.length
 * 1 <= val <= 10^5
 * 1 <= tot <= 10^9
 * 最多调用 add 和 count 函数各 1000 次
 */
public class FindSumPairs {

    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> map;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        map = new HashMap<>();
        for (int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) - 1);
        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int res = 0;
        for (int num : nums1) {
            res += map.getOrDefault(tot - num, 0);
        }
        return res;
    }

}
