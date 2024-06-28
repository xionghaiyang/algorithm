package com.sean.leetcode.LeetCode2659;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 16:48
 * @Description: https://leetcode.cn/problems/make-array-empty/description/
 * 2659. 将数组清空
 * 给你一个包含若干 互不相同 整数的数组 nums ，你需要执行以下操作 直到数组为空 ：
 * 如果数组中第一个元素是当前数组中的 最小值 ，则删除它。
 * 否则，将第一个元素移动到数组的 末尾 。
 * 请你返回需要多少个操作使 nums 为空。
 */
public class Solution {

    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (i, j) -> nums[i] - nums[j]);
        long res = n;
        for (int k = 1; k < n; k++) {
            if (index[k - 1] > index[k]) {
                res += n - k;
            }
        }
        return res;
    }

}
