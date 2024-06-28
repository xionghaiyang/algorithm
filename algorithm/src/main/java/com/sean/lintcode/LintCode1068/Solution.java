package com.sean.lintcode.LintCode1068;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-04 11:33
 * @Description: https://www.lintcode.com/problem/1068/?showListFe=true&page=1&pageSize=50
 * 给定一个整数数组nums，编写一个返回此数组的“中心索引”的方法。
 * 我们将中心索引定义为：中心索引左边的数字之和等于中心索引右边的数字之和。
 * 如果不存在这样的中心索引，我们应该返回-1。 如果有多个中心索引，则应返回最左侧的那个。
 */
public class Solution {

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

}
