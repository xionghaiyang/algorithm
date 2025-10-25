package com.sean.leetcode.LeetCode1716;

/**
 * @Author xionghaiyang
 * @Date 2025-10-25 19:11
 * @Description https://leetcode.cn/problems/calculate-money-in-leetcode-bank
 * 1716. 计算力扣银行的钱
 * Hercy 想要为购买第一辆车存钱。
 * 他 每天 都往力扣银行里存钱。
 * 最开始，他在周一的时候存入 1 块钱。
 * 从周二到周日，他每天都比前一天多存入 1 块钱。
 * 在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
 * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
 * 1 <= n <= 1000
 */
public class Solution {

    public int totalMoney(int n) {
        final int d = 7;
        int w = n / d, r = n % d;
        return (w + d) * d * w / 2 + (2 * w + r + 1) * r / 2;
    }

}
