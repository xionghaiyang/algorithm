package com.sean.leetcode.LeetCode1679;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 10:27
 * @Description: https://leetcode.cn/problems/max-number-of-k-sum-pairs/?envType=study-plan-v2&envId=leetcode-75
 * 1679. K 和数对的最大数目
 * 给你一个整数数组 nums 和一个整数 k 。
 * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
 * 返回你可以对数组执行的最大操作数。
 */
public class Solution {

    public int maxOperations1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (int num : nums) {
            if (map.containsKey(num) && map.containsKey(k - num)) {
                if (num == k - num && map.get(num) < 2) {
                    continue;
                }
                res++;
                map.put(num, map.getOrDefault(num, 0) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
                map.put(k - num, map.getOrDefault(k - num, 0) - 1);
                if (map.get(k - num) == 0) {
                    map.remove(k - num);
                }
            }
        }
        return res;
    }

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);//O(N*log(N))
        int res = 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                res++;
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

}
