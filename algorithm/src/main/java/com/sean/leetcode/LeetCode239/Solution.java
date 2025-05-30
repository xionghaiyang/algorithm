package com.sean.leetcode.LeetCode239;

import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2025-05-30 08:50
 * @Description https://leetcode.cn/problems/sliding-window-maximum
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        //大 -> 小
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int left = 0, right = 0; left < n - k + 1; left++) {
            while (right < n && right - left + 1 <= k) {
                while (!linkedList.isEmpty() && nums[right] > nums[linkedList.peekLast()]) {
                    linkedList.pollLast();
                }
                linkedList.addLast(right);
                right++;
            }
            res[left] = nums[linkedList.peekFirst()];
            if (linkedList.peekFirst() <= left) {
                linkedList.pollFirst();
            }
        }
        return res;
    }

}
