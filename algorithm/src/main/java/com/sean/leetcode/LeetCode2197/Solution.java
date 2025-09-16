package com.sean.leetcode.LeetCode2197;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-16 09:52
 * @Description https://leetcode.cn/problems/replace-non-coprime-numbers-in-array
 * 2197. 替换数组中的非互质数
 * 给你一个整数数组 nums 。
 * 请你对数组执行下述操作：
 * 从 nums 中找出 任意 两个 相邻 的 非互质 数。
 * 如果不存在这样的数，终止 这一过程。
 * 否则，删除这两个数，并 替换 为它们的 最小公倍数（Least Common Multiple，LCM）。
 * 只要还能找出两个相邻的非互质数就继续 重复 这一过程。
 * 返回修改后得到的 最终 数组。
 * 可以证明的是，以 任意 顺序替换相邻的非互质数都可以得到相同的结果。
 * 生成的测试用例可以保证最终数组中的值 小于或者等于 10^8 。
 * 两个数字 x 和 y 满足 非互质数 的条件是：GCD(x, y) > 1 ，其中 GCD(x, y) 是 x 和 y 的 最大公约数 。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 生成的测试用例可以保证最终数组中的值 小于或者等于 10^8 。
 */
public class Solution {

    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int num : nums) {
            while (!stack.isEmpty() && gcd(num, stack.peekLast()) > 1) {
                num = lcm(num, stack.pollLast());
            }
            stack.addLast(num);
        }
        return new ArrayList<>(stack);
    }

    private int lcm(int x, int y) {
        int gcd = gcd(x, y);
        return x / gcd * y;
    }

    private int gcd(int x, int y) {
        while (y != 0) {
            int t = x % y;
            x = y;
            y = t;
        }
        return x;
    }

}
