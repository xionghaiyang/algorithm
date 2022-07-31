package com.sean.lintcode;

public class LintCode1886 {

    public static void moveTarget(int[] nums, int target) {
        int count = 0;
        int left = nums.length - 1, right = nums.length - 1;
        while (left >= 0) {
            if (nums[left] != target) {
                nums[right] = nums[left];
                right--;
            } else {
                count++;
            }
            left--;
        }
        for (int i = 0; i < count; i++) {
            nums[i] = target;
        }
    }

}
