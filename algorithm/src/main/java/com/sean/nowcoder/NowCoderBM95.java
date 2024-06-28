package com.sean.nowcoder;

public class NowCoderBM95 {

    public static int candy(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int n = arr.length;
        if (n <= 1) {
            return n;
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                nums[i] = nums[i - 1] + 1;
            }
        }
        int res = nums[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1] && nums[i] <= nums[i + 1]) {
                nums[i] = nums[i + 1] + 1;
            }
            res += nums[i];
        }
        return res;
    }

}
