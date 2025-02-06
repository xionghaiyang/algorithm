package com.sean.leetcode.LeetCode47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-02-06 09:35
 * @Description https://leetcode.cn/problems/permutations-ii
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class Solution {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    int n;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        n = nums.length;
        boolean[] visited = new boolean[n];
        process(nums, visited, 0);
        return res;
    }

    private void process(int[] nums, boolean[] visited, int size) {
        if (n == size) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            process(nums, visited, size + 1);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

}
