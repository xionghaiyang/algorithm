package com.sean.leetcode.LeetCode2735;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-28 15:23
 * @Description: https://leetcode.cn/problems/collecting-chocolates/description/
 * 2735. 收集巧克力
 * 给你一个长度为 n 、下标从 0 开始的整数数组 nums ，表示收集不同巧克力的成本。
 * 每个巧克力都对应一个不同的类型，最初，位于下标 i 的巧克力就对应第 i 个类型。
 * 在一步操作中，你可以用成本 x 执行下述行为：
 * 同时修改所有巧克力的类型，将巧克力的类型 ith 修改为类型 ((i + 1) mod n)th。
 * 假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。
 */
public class Solution {

    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] arr = new int[n];
        System.arraycopy(nums, 0, arr, 0, n);
        long res = getSum(arr);
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n; i++) {
                arr[i] = Math.min(arr[i], nums[(i + k) % n]);
            }
            res = Math.min(res, (long) k * x + getSum(arr));
        }
        return res;
    }

    private long getSum(int[] arr) {
        long res = 0;
        for (int num : arr) {
            res += num;
        }
        return res;
    }

    public long minCost1(int[] nums, int x) {
        int n = nums.length;
        int minIndex = 0;
        //找出nums中最小的元素，并用其为首元素构造一个新的数组
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = nums[(minIndex + i) % n];
        }
        System.arraycopy(tmp, 0, nums, 0, n);
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = n - 1;
        //循环来看，右侧num[0]是更小的元素，但是不一定是第一个更小的元素，需要用单调栈计算得到
        for (int i = 0; i < n; i++) {
            right[i] = n - i - 1;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                right[stack.peek()] = i - stack.peek() - 1;
                stack.pop();
            }
            left[i] = i - stack.peek() - 1;
            stack.push(i);
        }
        long[] F = new long[n];
        //进行操作需要的成本
        diffTwice(F, 0, n - 1, x, 0);
        for (int i = 0; i < n; i++) {
            int minv = Math.min(left[i], right[i]);
            int maxv = Math.max(left[i], right[i]);
            //第一种情况,窗口数量k+1,总和num[i]*k+num[i]
            diffTwice(F, 0, minv, nums[i], nums[i]);
            //第二情况，窗口数量minv+1,总和0*k+num[i]*(minv+1)
            diffTwice(F, minv + 1, maxv, 0, (long) nums[i] * (minv + 1));
            //第三种情况，窗口数量left[i]+right[i]-k+1,总和-num[i]*k+num[i]*(left[i]+right[i]+1)
            diffTwice(F, maxv + 1, left[i] + right[i], -nums[i], (long) nums[i] * (left[i] + right[i] + 1));
        }
        //计算两次前缀和
        for (int i = 0; i < 2; i++) {
            long[] G = new long[n];
            G[0] = F[0];
            for (int j = 1; j < n; j++) {
                G[j] = G[j - 1] + F[j];
            }
            System.arraycopy(G, 0, F, 0, n);
        }
        long res = Long.MAX_VALUE;
        for (long num : F) {
            res = Math.min(res, num);
        }
        return res;
    }

    //辅助函数，二次差分，将F[left,right]增加ki+b,i是下标
    private void diffTwice(long[] F, int left, int right, long k, long b) {
        if (left > right) {
            return;
        }
        diffOnce(F, left, left, k * left + b);
        diffOnce(F, left + 1, right, k);
        diffOnce(F, right + 1, right + 1, -(k * right + b));
    }

    //辅助函数，一次差分，将F[left..right]都增加d
    private void diffOnce(long[] F, int left, int right, long d) {
        if (left > right) {
            return;
        }
        int n = F.length;
        if (left < n) {
            F[left] += d;
        }
        if (right + 1 < n) {
            F[right + 1] -= d;
        }
    }

}
