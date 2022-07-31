package com.sean.nowcoder;

public class NowCoderBM93 {

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int capacity = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, capacity);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

}
