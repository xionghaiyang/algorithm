package com.sean.leetcode.LeetCode1018;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-11-24 12:12
 * @Description https://leetcode.cn/problems/binary-prefix-divisible-by-5
 * 1018. 可被 5 整除的二进制前缀
 * 给定一个二进制数组 nums ( 索引从0开始 )。
 * 我们将xi 定义为其二进制表示形式为子数组 nums[0..i] (从最高有效位到最低有效位)。
 * 例如，如果 nums =[1,0,1] ，那么 x0 = 1, x1 = 2, 和 x2 = 5。
 * 返回布尔值列表 answer，只有当 xi 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 * 1 <= nums.length <= 10^5
 * nums[i] 仅为 0 或 1
 */
public class Solution {

    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();
        int x = 0;
        for (int num : nums) {
            x = ((x << 1) | num) % 5;
            res.add(x == 0);
        }
        return res;
    }

}
