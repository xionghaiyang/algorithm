package com.sean.leetcode.LeetCode3589;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2025-06-25 15:19
 * @Description https://leetcode.cn/problems/count-prime-gap-balanced-subarrays
 * 3589. 计数质数间隔平衡子数组
 * 给定一个整数数组 nums 和一个整数 k。
 * 子数组 被称为 质数间隔平衡，如果：
 * 其包含 至少两个质数，并且
 * 该 子数组 中 最大 和 最小 质数的差小于或等于 k。
 * 返回 nums 中质数间隔平衡子数组的数量。
 * 注意：
 * 子数组 是数组中连续的 非空 元素序列。
 * 质数是大于 1 的自然数，它只有两个因数，即 1 和它本身。
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 5 * 10^4
 * 0 <= k <= 5 * 10^4
 */
public class Solution {

    private static final int MAX = 500_001;
    private static final boolean[] IS_PRIME = new boolean[MAX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.fill(IS_PRIME, true);
        IS_PRIME[0] = false;
        IS_PRIME[1] = false;
        for (int i = 2; i * i < MAX; i++) {
            if (!IS_PRIME[i]) {
                continue;
            }
            for (int j = i * i; j < MAX; j += i) {
                IS_PRIME[j] = false;
            }
        }
    }

    public int primeSubarray(int[] nums, int k) {
        init();
        //小 -> 大
        LinkedList<Integer> min = new LinkedList<>();
        //大 -> 小
        LinkedList<Integer> max = new LinkedList<>();
        int n = nums.length;
        int res = 0;
        for (int i = 0, left = 0, last = -1, last2 = -1; i < n; i++) {
            int x = nums[i];
            if (IS_PRIME[x]) {
                //入
                last2 = last;
                last = i;
                while (!min.isEmpty() && x <= nums[min.peekLast()]) {
                    min.pollLast();
                }
                min.addLast(i);
                while (!max.isEmpty() && x >= nums[max.peekLast()]) {
                    max.pollLast();
                }
                max.addLast(i);
                //出
                while (nums[max.peekFirst()] - nums[min.peekFirst()] > k) {
                    left++;
                    if (min.peekFirst() < left) {
                        min.pollFirst();
                    }
                    if (max.peekFirst() < left) {
                        max.pollFirst();
                    }
                }
            }
            res += last2 - left + 1;
        }
        return res;
    }

}
