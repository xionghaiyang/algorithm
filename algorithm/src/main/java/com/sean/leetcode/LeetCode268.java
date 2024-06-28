package com.sean.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 丢失的数字
 * https://leetcode-cn.com/problems/missing-number/
 */
public class LeetCode268 {

    //排序
    public static int missingNumber1(int[] nums) {
        Arrays.sort(nums);
        //判断n是否在末位
        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        }
        //判断0是否在首位
        if (nums[0] != 0) {
            return 0;
        }
        //此时缺失的数字一定在(0,n)中
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i - 1] + 1;
            if (nums[i] != expectedNum) {
                return expectedNum;
            }
        }
        return -1;
    }

    //哈希表
    public static int missingNumber2(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int i = 0; i < nums.length + 1; i++) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    //位运算
    public static int missingNumber3(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    //数学
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

}
