package com.sean.leetcode.LeetCode229;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-12 18:49
 * @Description https://leetcode.cn/problems/majority-element-ii
 * 229. 多数元素 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * 1 <= nums.length <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public List<Integer> majorityElement(int[] nums) {
        int element1 = 0, element2 = 0;
        int vote1 = 0, vote2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                vote1++;
            } else if (vote2 > 0 && num == element2) {
                vote2++;
            } else if (vote1 == 0) {
                element1 = num;
                vote1++;
            } else if (vote2 == 0) {
                element2 = num;
                vote2++;
            } else {
                vote1--;
                vote2--;
            }
        }
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        List<Integer> res = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            res.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            res.add(element2);
        }
        return res;
    }

}
