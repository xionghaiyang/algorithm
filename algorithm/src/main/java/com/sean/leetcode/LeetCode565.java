package com.sean.leetcode;

public class LeetCode565 {

    public int arrayNesting1(int[] nums) {
        int res = 0, cnt = 0, n = nums.length;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            cnt = 0;
            while (!visit[i]) {
                visit[i] = true;
                i = nums[i];
                cnt++;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

    public int arrayNesting2(int[] nums) {
        int res = 0, cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            cnt = 0;
            while (nums[i] < n) {
                int num = nums[i];
                nums[i] = n;
                i = num;
                cnt++;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

}
