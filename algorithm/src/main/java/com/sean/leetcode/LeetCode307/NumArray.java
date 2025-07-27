package com.sean.leetcode.LeetCode307;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-13 16:30
 * @Description: https://leetcode.cn/problems/range-sum-query-mutable
 * 307. 区域和检索 - 数组可修改
 * 给你一个数组 nums ，请你完成两类查询。
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left
 * ，和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 10^4
 */
public class NumArray {

    private int[] nums;
    private int[] tree;

    public NumArray(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        tree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] += nums[i - 1];
            int next = i + (i & -i);
            if (next <= n) {
                tree[next] += tree[i];
            }
        }
    }

    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        for (int i = index + 1; i < tree.length; i += i & -i) {
            tree[i] += delta;
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    private int prefixSum(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i &= i - 1;//i-=i & -i
        }
        return res;
    }

}
