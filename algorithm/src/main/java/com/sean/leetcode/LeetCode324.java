package com.sean.leetcode;

import java.util.Arrays;

public class LeetCode324 {

    public static void wiggleSort(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int mid = (n + 1) >> 1;
        for (int i = 0, j = mid - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

}
