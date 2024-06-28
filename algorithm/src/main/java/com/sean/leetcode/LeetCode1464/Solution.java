package com.sean.leetcode.LeetCode1464;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-26 08:32
 * @Description: https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array/
 * 1464. 数组中两元素的最大乘积
 * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 * 请你计算并返回该式的最大值。
 */
public class Solution {

    public int maxProduct1(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        priorityQueue.offer(nums[0]);
        priorityQueue.offer(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }
        }
        return (priorityQueue.poll() - 1) * (priorityQueue.poll() - 1);
    }

    public int maxProduct(int[] nums) {
        int first = nums[0], second = nums[1];
        if (first < second) {
            int temp = first;
            first = second;
            second = temp;
        }
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > first) {
                second = first;
                first = nums[i];
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        return (first - 1) * (second - 1);
    }

}
