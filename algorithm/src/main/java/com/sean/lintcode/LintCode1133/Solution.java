package com.sean.lintcode.LintCode1133;

/**
 * @Author xionghaiyang
 * @Date 2022-09-03 09:35
 * @Description https://www.lintcode.com/problem/1133/?showListFe=true&page=1&pageSize=50
 * 1133 · 团购
 * 描述
 * 有 x 个人打算买 A 类商品，有 y 个人打算买 B 类商品，z 个人打算买 C 类商品，每个人都只打算买 1 件商品。
 * 现在有一个团购规则，规则如下：
 * 每次团购规定买 3 件商品。
 * 每次团购至少包括 1 件 A 类商品和 1 件 B 类商品。
 * 请求出这些人最多团购多少次。
 */
public class Solution {

    public int groupBuyTimes(int x, int y, int z) {
        return Math.min(Math.min(x, y), (x + y + z) / 3);
    }

}
