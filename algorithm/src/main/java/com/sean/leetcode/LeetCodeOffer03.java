package com.sean.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/3/28 11:50
 * @Description: https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * 数组中重复的数字
 * @version: 1.0
 */
public class LeetCodeOffer03 {

    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }

}
