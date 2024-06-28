package com.sean.leetcode.LeetCode2122;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-28 17:34
 * @Description: https://leetcode.cn/problems/recover-the-original-array/description/
 * 2122. 还原原数组
 * Alice 有一个下标从 0 开始的数组 arr ，由 n 个正整数组成。
 * 她会选择一个任意的 正整数 k 并按下述方式创建两个下标从 0 开始的新整数数组 lower 和 higher ：
 * 对每个满足 0 <= i < n 的下标 i ，lower[i] = arr[i] - k
 * 对每个满足 0 <= i < n 的下标 i ，higher[i] = arr[i] + k
 * 不幸地是，Alice 丢失了全部三个数组。
 * 但是，她记住了在数组 lower 和 higher 中出现的整数，但不知道每个整数属于哪个数组。
 * 请你帮助 Alice 还原原数组。
 * 给你一个由 2n 个整数组成的整数数组 nums ，其中 恰好 n 个整数出现在 lower ，剩下的出现在 higher ，还原并返回 原数组 arr 。
 * 如果出现答案不唯一的情况，返回 任一 有效数组。
 * 注意：生成的测试用例保证存在 至少一个 有效数组 arr 。
 */
public class Solution {

    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        //外层循环枚举k2(也就是k*2)
        for (int i = 1; i < n; i++) {
            int k2 = nums[i] - nums[0];
            //验证k2的可行性
            if (k2 == 0 || k2 % 2 == 1) {
                continue;
            }
            int[] res = new int[n / 2];
            int index = 0;
            boolean[] visit = new boolean[n];
            //lower从0开始
            int left = 0;
            //higher从i开始
            int right = i;
            for (int j = 0; j < n / 2; j++) {
                //找到第一个lower元素，（也就是从前到后第一个未被放入higher的元素）
                while (left < n && visit[left]) {
                    left++;
                }
                //找到与上述lower元素对应的higher元素
                while (right < n && nums[right] - nums[left] != k2) {
                    right++;
                }
                //未找到则说明k2不符合要求
                if (right == n) {
                    break;
                }
                //该元素已被放入到higher中
                visit[right] = true;
                res[index++] = nums[left] + k2 / 2;
                left++;
                right++;
            }
            //如果lower和higher中各有n/2元素，说明k2符合条件
            if (index == n / 2) {
                return res;
            }
        }
        return new int[0];
    }

}
