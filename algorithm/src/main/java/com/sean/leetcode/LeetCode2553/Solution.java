package com.sean.leetcode.LeetCode2553;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autnor: xionghaiyang
 * @Date: 2026-05-11 06:15
 * @Description: https://leetcode.cn/problems/separate-the-digits-in-an-array
 * 2553. 分割数组中数字的数位
 * 给你一个正整数数组 nums ，请你返回一个数组 answer ，你需要将 nums 中每个整数进行数位分割后，按照 nums 中出现的 相同顺序 放入答案数组中。
 * 对一个整数进行数位分割，指的是将整数各个数位按原本出现的顺序排列成数组。
 * 比方说，整数 10921 ，分割它的各个数位得到 [1,0,9,2,1] 。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            for (char c : String.valueOf(num).toCharArray()) {
                list.add(c - '0');
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
