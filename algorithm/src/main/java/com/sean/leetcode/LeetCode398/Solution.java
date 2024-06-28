package com.sean.leetcode.LeetCode398;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-03 14:26
 * @Description: https://leetcode.cn/problems/random-pick-index/description/
 * 398. 随机数索引
 * 给你一个可能含有 重复元素 的整数数组 nums ，请你随机输出给定的目标数字 target 的索引。
 * 你可以假设给定的数字一定存在于数组中。
 * 实现 Solution 类：
 * Solution(int[] nums) 用数组 nums 初始化对象。
 * int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。
 * 如果存在多个有效的索引，则每个索引的返回概率应当相等。
 */
public class Solution {

    Map<Integer, List<Integer>> map;
    Random random;

    public Solution(int[] nums) {
        map = new HashMap<>();
        random = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                map.put(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(random.nextInt(list.size()));
    }

}
