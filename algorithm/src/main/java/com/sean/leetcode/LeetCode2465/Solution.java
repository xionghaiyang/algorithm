package com.sean.leetcode.LeetCode2465;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-05 10:50
 * @Description: https://leetcode.cn/problems/number-of-distinct-averages/
 * 2465. 不同的平均值数目
 * 给你一个下标从 0 开始长度为 偶数 的整数数组 nums 。
 * 只要 nums 不是 空数组，你就重复执行以下步骤：
 * 找到 nums 中的最小值，并删除它。
 * 找到 nums 中的最大值，并删除它。
 * 计算删除两数的平均值。
 * 两数 a 和 b 的 平均值 为 (a + b) / 2 。
 * 比方说，2 和 3 的平均值是 (2 + 3) / 2 = 2.5 。
 * 返回上述过程能得到的 不同 平均值的数目。
 * 注意 ，如果最小值或者最大值有重复元素，可以删除任意一个。
 */
public class Solution {

    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            set.add(nums[i] + nums[j]);
        }
        return set.size();
    }

}
