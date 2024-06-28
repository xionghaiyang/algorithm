package com.sean.leetcode;

/**
 * 将数组分成和相等的三个部分
 * https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
 */
public class LeetCode1013 {

    public static boolean canThreePartsEqualSum(int[] arr) {
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        double target = (double) preSum[arr.length - 1] / 3;
        int left = -1;
        for (int i = 0; i < preSum.length; i++) {
            if (preSum[i] == target) {
                left = i;
                break;
            }
        }
        if (left == -1) {
            return false;
        }
        int right = -1;
        for (int i = left + 1; i < preSum.length; i++) {
            if (preSum[i] - preSum[left] == target) {
                right = i;
                break;
            }
        }
        if (right == -1 || right == preSum.length - 1) {
            return false;
        }else {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(canThreePartsEqualSum(new int[]{0,2,1,-6,6,-7,9,1,2,0,1}));
        System.out.println(canThreePartsEqualSum(new int[]{0,2,1,-6,6,7,9,-1,2,0,1}));
        System.out.println(canThreePartsEqualSum(new int[]{3,3,6,5,-2,2,5,1,-9,4}));
    }

}
