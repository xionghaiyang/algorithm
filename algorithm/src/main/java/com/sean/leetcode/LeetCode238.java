package com.sean.leetcode;

/**
 * 除自身以外数组的乘积
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 */
public class LeetCode238 {

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        //L和R分别表示左右两侧的乘积列表
        int[] L = new int[length];
        int[] R = new int[length];
        int[] res = new int[length];
        //L[i]为索引i左侧所有元素的乘积
        //对于索引为'0'的元素,因为左侧没有元素，所以L[0]=1
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }
        //R[i]为索引右侧所有元素的乘积
        //对于索引为'length-1'的元素，因为右侧没有元素,所以R[length-1] = 1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        //对于索引i,除nums[i]之外其余个元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        for (int i = 0; i < length; i++) {
            res[i] = L[i] * R[i];
        }
        return res;
    }
}
