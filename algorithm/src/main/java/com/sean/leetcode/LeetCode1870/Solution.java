package com.sean.leetcode.LeetCode1870;

/**
 * @Author xionghaiyang
 * @Date 2024-10-08 13:20
 * @Description https://leetcode.cn/problems/minimum-speed-to-arrive-on-time
 * 1870. 准时到达的列车最小时速
 * 给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。
 * 要到达办公室，你必须按给定次序乘坐 n 趟列车。
 * 另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。
 * 每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
 * 例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
 * 返回能满足你在时限前到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
 * 生成的测试用例保证答案不超过 10^7 ，且 hour 的 小数点后最多存在两位数字 。
 * n == dist.length
 * 1 <= n <= 10^5
 * 1 <= dist[i] <= 10^5
 * 1 <= hour <= 10^9
 * hours 中，小数点后最多存在两位数字
 */
public class Solution {

    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        //将hour乘100转为整数
        long hr = Math.round(hour * 100);
        //时间必须要大于路程段数减1
        if (hr <= (n - 1) * 100) {
            return -1;
        }
        //二分
        int left = 1, right = 10_000_000;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //判断当前时速是否满足时限
            long t = 0;
            //前n-1段中第i段贡献的时间：ceil(dist[i]/mid)
            for (int i = 0; i < n - 1; i++) {
                t += (dist[i] - 1) / mid + 1;
            }
            //最后一段贡献的时间:dist[n-1]/mid
            t *= mid;
            t += dist[n - 1];
            //通分以转化为整数比较
            if (t * 100 <= hr * mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        //满足条件的最小时速
        return left;
    }

}
