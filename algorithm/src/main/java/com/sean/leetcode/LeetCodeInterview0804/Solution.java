package com.sean.leetcode.LeetCodeInterview0804;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-14 18:50
 * @Description https://leetcode.cn/problems/power-set-lcci
 * 面试题 08.04. 幂集
 * 幂集。
 * 编写一种方法，返回某集合的所有子集。
 * 集合中 不包含重复的元素。
 * 说明：解集不能包含重复的子集。
 */
public class Solution {

    private List<List<Integer>> res;
    private List<Integer> temp;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        process(nums, 0);
        return res;
    }

    private void process(int[] nums, int i) {
        if (i == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[i]);
        process(nums, i + 1);
        temp.remove(temp.size() - 1);
        process(nums, i + 1);
    }

}
