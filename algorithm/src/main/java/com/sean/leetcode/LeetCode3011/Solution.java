package com.sean.leetcode.LeetCode3011;

/**
 * @Author xionghaiyang
 * @Date 2024-07-13 07:11
 * @Description https://leetcode.cn/problems/find-if-array-can-be-sorted/description/
 * 3011. 判断一个数组是否可以变为有序
 * 给你一个下标从 0 开始且全是 正 整数的数组 nums 。
 * 一次 操作 中，如果两个 相邻 元素在二进制下数位为 1 的数目 相同 ，那么你可以将这两个元素交换。
 * 你可以执行这个操作 任意次 （也可以 0 次）。
 * 如果你可以使数组变有序，请你返回 true ，否则返回 false 。
 */
public class Solution {

    public boolean canSortArray(int[] nums) {
        int lastCnt = 0;
        int lastGroupMax = 0;
        int curGroupMax = 0;
        for (int num : nums) {
            int curCnt = Integer.bitCount(num);
            if (curCnt == lastCnt) {
                curGroupMax = Math.max(curGroupMax, num);
            } else {
                lastCnt = curCnt;
                lastGroupMax = curGroupMax;
                curGroupMax = num;
            }
            if (num < lastGroupMax) {
                return false;
            }
        }
        return true;
    }

}
