package com.sean.leetcode.LeetCode2011;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-26 15:43
 * @Description: https://leetcode.cn/problems/final-value-of-variable-after-performing-operations/
 * 2011. 执行操作后的变量值
 * 存在一种仅支持 4 种操作和 1 个变量 X 的编程语言：
 * ++X 和 X++ 使变量 X 的值 加 1
 * --X 和 X-- 使变量 X 的值 减 1
 * 最初，X 的值是 0
 * 给你一个字符串数组 operations ，这是由操作组成的一个列表，返回执行所有操作后， X 的 最终值 。
 */
public class Solution {

    public int finalValueAfterOperations(String[] operations) {
        if (operations == null || operations.length == 0) {
            return 0;
        }
        int res = 0;
        for (String operation : operations) {
            if ("++X".equals(operation) || "X++".equals(operation)) {
                res++;
            } else if ("--X".equals(operation) || "X--".equals(operation)) {
                res--;
            }
        }
        return res;
    }

}
