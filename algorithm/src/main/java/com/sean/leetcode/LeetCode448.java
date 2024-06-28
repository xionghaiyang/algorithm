package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode448 {

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int[] visit = new int[n + 1];
        for (int num : nums) {
            if (num >= 1 && num <= n) {
                visit[num]++;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (visit[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1) % n] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }

}

