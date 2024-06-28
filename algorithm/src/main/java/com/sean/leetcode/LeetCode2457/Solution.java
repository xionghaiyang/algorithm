package com.sean.leetcode.LeetCode2457;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-13 17:20
 * @Description: https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful/description/
 * 2457. 美丽整数的最小增量
 * 给你两个正整数 n 和 target 。
 * 如果某个整数每一位上的数字相加小于或等于 target ，则认为这个整数是一个 美丽整数 。
 * 找出并返回满足 n + x 是 美丽整数 的最小非负整数 x 。生成的输入保证总可以使 n 变成一个美丽整数。
 */
public class Solution {

    public long makeIntegerBeautiful(long n, int target) {
        //将n转换成字符串，并直接获取n的数字位数长度
        int length = Long.toString(n).length();
        //base:进位基准 例如9,99,999,9999等
        //mul:取模基准 例如 10,100,1000,10000等
        long base = 0, mul = 1;
        //minn：符合的美丽证书最小变化，若初始数字就符合，则直接增量为0
        long minn = getSum(n) <= target ? 0 : Long.MAX_VALUE;
        //美剧所有符合的数
        for (int i = 1; i <= length; i++) {
            //进位基准增加，例如99->999
            base = base * 10 + 9;
            //驱魔基准增加，例如10->100
            mul *= 10;
            Long temp = (n + base) / mul * mul;
            if (getSum(temp) <= target) {
                minn = Math.min(minn, temp - n);
            }
        }
        return minn;
    }

    //获取输入数的每一位数字的和，例如151=1+5+1=7
    private int getSum(Long n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }

}
