package com.sean.leetcode.LeetCode2294;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-19 06:17
 * @Description https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k
 * 2294. 划分数组使最大差为 K
 * 给你一个整数数组 nums 和一个整数 k 。
 * 你可以将 nums 划分成一个或多个 子序列 ，使 nums 中的每个元素都 恰好 出现在一个子序列中。
 * 在满足每个子序列中最大值和最小值之间的差值最多为 k 的前提下，返回需要划分的 最少 子序列数目。
 * 子序列 本质是一个序列，可以通过删除另一个序列中的某些元素（或者不删除）但不改变剩下元素的顺序得到。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 0 <= k <= 10^5
 */
public class Solution {

    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 1;
        for (int left = 0, right = 0; right < n; right++) {
            if (nums[right] - nums[left] > k) {
                res++;
                left = right;
            }
        }
        return res;
    }

    public int partitionArray1(int[] nums, int k) {
        int max = nums[0], min = nums[0];
        for (int num : nums) {
            if (max < num) {
                max = num;
            } else if (min > num) {
                min = num;
            }
        }
        if (max - min <= k) {
            return 1;
        }
        if (max - min <= 2 * k) {
            return 2;
        }
        int n = max - min + 1;
        boolean[] bucket = new boolean[n];
        for (int num : nums) {
            bucket[num - min] = true;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (bucket[i]) {
                res++;
                i += k;
            }
        }
        return res;
    }

}
