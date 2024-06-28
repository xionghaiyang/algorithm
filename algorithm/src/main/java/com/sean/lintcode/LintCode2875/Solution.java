package com.sean.lintcode.LintCode2875;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-30 08:25
 * @Description: https://www.lintcode.com/problem/2875/?showListFe=true&page=1&pageSize=50
 * 2875 · 程序运行结果
 * 描述
 * 给定一个数字 num 表示 new TestB() 的次数，请您计算执行 num 次 new TestB() 后 TestA.num 的值，
 * 并在 Solution 类的 printNum 方法中输出。
 */
public class Solution {

    public void printNum(int num) {
        for (int i = 0; i < num; i++) {
            new TestB();
        }
        System.out.println(TestA.num);
    }

}
