package com.sean.leetcode;

public class LeetCode857 {

    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        int res = right;
        while (left < right) {
            int speed = left + ((right - left) >> 1);
            if (getTime(piles, speed) <= h) {
                res = speed;
                right = speed;
            } else {
                left = speed + 1;
            }
        }
        return res;
    }

    public static long getTime(int[] piles, int speed) {
        int res = 0;
        for (int pile : piles) {
            res += (pile + speed - 1) / speed;
        }
        return res;
    }

}
