package com.sean.lintcode;

import java.util.List;

public class LintCode46 {

    public static int majorityNumber(List<Integer> nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count * 2 > nums.size() ? candidate : -1;
    }

}
