package com.sean.leetcode.LeetCode1224;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-18 11:33
 * @Description: https://leetcode.cn/problems/maximum-equal-frequency/
 * 1224. 最大相等频率
 * 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 */
public class Solution {

    public int maxEqualFreq(int[] nums) {
        //freq记录出现次数为x的数的数目
        Map<Integer, Integer> freq = new HashMap<>();
        //count记录数x出现的次数
        Map<Integer, Integer> count = new HashMap<>();
        //maxFreq最大出现次数
        int res = 0, maxFreq = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count.getOrDefault(nums[i], 0) > 0) {
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(nums[i]));
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
            //最大出现次数为1。
            //所有数的出现次数都是maxFreq或maxFreq-1,并且最大出现次数只有1个。
            //其中一个数的次数为1，其余数出现的次数都是maxFreq
            boolean flag = maxFreq == 1 ||
                    freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == i + 1 && freq.get(maxFreq) == 1 ||
                    freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1;
            if (flag) {
                res = Math.max(res, i + 1);
            }
        }
        return res;
    }

}
