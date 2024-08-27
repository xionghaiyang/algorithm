package com.sean.leetcode.LeetCode3134;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-08-27 10:28
 * @Description https://leetcode.cn/problems/find-the-median-of-the-uniqueness-array/
 * 3134. 找出唯一性数组的中位数
 * 给你一个整数数组 nums 。
 * 数组 nums 的 唯一性数组 是一个按元素从小到大排序的数组，包含了 nums 的所有非空子数组中不同元素的个数。
 * 换句话说，这是由所有 0 <= i <= j < nums.length 的 distinct(nums[i..j]) 组成的递增数组。
 * 其中，distinct(nums[i..j]) 表示从下标 i 到下标 j 的子数组中不同元素的数量。
 * 返回 nums 唯一性数组 的 中位数 。
 * 注意，数组的 中位数 定义为有序数组的中间元素。
 * 如果有两个中间元素，则取值较小的那个。
 */
public class Solution {

    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        long median = ((long) n * (n + 1) / 2 + 1) / 2;
        int res = 0;
        int low = 1, high = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (check(nums, mid, median)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    //检测数组中不同元素数目小于等于t的连续子数组数目是否大于等于median
    private boolean check(int[] nums, int t, long median) {
        Map<Integer, Integer> map = new HashMap<>();
        long total = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.size() > t) {
                map.put(nums[j], map.get(nums[j]) - 1);
                if (map.get(nums[j]) == 0) {
                    map.remove(nums[j]);
                }
                j++;
            }
            total += i - j + 1;
        }
        return total >= median;
    }

}
