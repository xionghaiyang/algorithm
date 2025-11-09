package com.sean.leetcode.LeetCode2169;

/**
 * @Author xionghaiyang
 * @Date 2025-11-09 12:24
 * @Description https://leetcode.cn/problems/count-operations-to-obtain-zero
 * 2169. 得到 0 的操作数
 * 给你两个 非负 整数 num1 和 num2 。
 * 每一步 操作 中，如果 num1 >= num2 ，你必须用 num1 减 num2 ；否则，你必须用 num2 减 num1 。
 * 例如，num1 = 5 且 num2 = 4 ，应该用 num1 减 num2 ，因此，得到 num1 = 1 和 num2 = 4 。
 * 然而，如果 num1 = 4且 num2 = 5 ，一步操作后，得到 num1 = 4 和 num2 = 1 。
 * 返回使 num1 = 0 或 num2 = 0 的 操作数 。
 * 0 <= num1, num2 <= 10^5
 */
public class Solution {

    public int countOperations(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }
        if (num1 > num2) {
            return countOperations(num1 % num2, num2) + num1 / num2;
        } else {
            return countOperations(num2 % num1, num1) + num2 / num1;
        }
    }

}
