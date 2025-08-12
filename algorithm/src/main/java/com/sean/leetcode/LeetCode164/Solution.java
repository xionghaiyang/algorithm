package com.sean.leetcode.LeetCode164;

/**
 * @Author xionghaiyang
 * @Date 2025-08-12 16:09
 * @Description https://leetcode.cn/problems/maximum-gap
 * 164. 最大间距
 * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。
 * 如果数组元素个数小于 2，则返回 0 。
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class Solution {

    public int maximumGap(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (max - min <= 1) {
            return max - min;
        }
        int n = nums.length;
        //答案至少是d
        int d = (max - min + n - 2) / (n - 1);
        int[][] buckets = new int[(max - min) / d + 1][2];
        for (int[] bucket : buckets) {
            bucket[0] = Integer.MAX_VALUE;
            bucket[1] = Integer.MIN_VALUE;
        }
        for (int num : nums) {
            int[] bucket = buckets[(num - min) / d];
            bucket[0] = Math.min(bucket[0], num);
            bucket[1] = Math.max(bucket[1], num);
        }
        int res = 0;
        int preMax = Integer.MAX_VALUE;
        for (int[] bucket : buckets) {
            if (bucket[0] != Integer.MAX_VALUE) {
                //桶内最小值，减去上一个非空桶的最大值
                res = Math.max(res, bucket[0] - preMax);
                preMax = bucket[1];
            }
        }
        return res;
    }

}
