package com.sean.leetcode.LeetCode3886;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-20 06:14
 * @Description: https://leetcode.cn/problems/sum-of-sortable-integers
 * 3886. 可排序整数求和
 * 给你一个长度为 n 的整数数组 nums。
 * 如果一个整数 k 满足以下条件，则称其为 可排序整数：k 是 n 的 因数，且可以通过依次执行以下操作将 nums 排序为 非递减顺序：
 * 将 nums 划分为长度为 k 的 连续子数组。
 * 独立地对每个子数组进行循环移动（左移或右移任意次数）。
 * 返回所有可能的可排序整数 k 的和。
 * 子数组 是数组中的一个连续、非空元素序列。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        //nums[nextDec[i]] > nums[nextDec[i]+1]
        int[] nextDec = new int[n];
        nextDec[n - 1] = n;
        //对于每一个i，记录下一个递减的位置
        for (int i = n - 2, p = n; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                p = i;
            }
            nextDec[i] = p;
        }
        int res = 0;
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                res += process(nums, nextDec, k);
                if (k * k < n) {
                    res += process(nums, nextDec, n / k);
                }
            }
        }
        return res;
    }

    private int process(int[] nums, int[] nextDec, int k) {
        int n = nums.length;
        for (int right = k - 1, lastMax = 0; right < n; right += k) {
            int left = right - k + 1;
            int mid = nextDec[left];
            if (mid >= right) {
                //[left,right]是递增的，最小值是nums[left],最大值为nums[right]
                //最小值必须>=上一段的最大值
                if (nums[left] < lastMax) {
                    return 0;
                }
                lastMax = nums[right];
            } else {
                //[left,mid]是第一段,[mid+1,right]是第二段
                //第二段必须是递增的，且第二段的最小值必须>=上一段的最大值，且第二段的最大值必须<=第一段的最小值
                if (nextDec[mid + 1] < right || nums[mid + 1] < lastMax || nums[right] > nums[left]) {
                    return 0;
                }
                lastMax = nums[mid];
            }
        }
        return k;
    }

}
