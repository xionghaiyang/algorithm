package com.sean.leetcode.LeetCode2437;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-09 08:09
 * @Description: https://leetcode.cn/problems/number-of-valid-clock-times/
 * 2437. 有效时间的数目
 * 给你一个长度为 5 的字符串 time ，表示一个电子时钟当前的时间，格式为 "hh:mm" 。
 * 最早 可能的时间是 "00:00" ，最晚 可能的时间是 "23:59" 。
 * 在字符串 time 中，被字符 ? 替换掉的数位是 未知的 ，被替换的数字可能是 0 到 9 中的任何一个。
 * 请你返回一个整数 answer ，将每一个 ? 都用 0 到 9 中一个数字替换后，可以得到的有效时间的数目。
 */
public class Solution {

    int res = 0;

    public int countTime1(String time) {
        char[] arr = time.toCharArray();
        dfs(arr, 0);
        return res;
    }

    private void dfs(char[] arr, int pos) {
        if (pos == arr.length) {
            if (check(arr)) {
                res++;
            }
            return;
        }
        if (arr[pos] == '?') {
            for (int i = 0; i <= 9; i++) {
                arr[pos] = (char) ('0' + i);
                dfs(arr, pos + 1);
                arr[pos] = '?';
            }
        } else {
            dfs(arr, pos + 1);
        }
    }

    private boolean check(char[] arr) {
        int hh = (arr[0] - '0') * 10 + (arr[1] - '0');
        int mm = (arr[3] - '0') * 10 + (arr[4] - '0');
        return hh >= 0 && hh < 24 && mm >= 0 && mm < 60;
    }

    public int countTime(String time) {
        int countHour = 0;
        int countMinute = 0;
        for (int i = 0; i < 24; i++) {
            int hiHour = i / 10;
            int loHour = i % 10;
            if ((time.charAt(0) == '?' || time.charAt(0) == hiHour + '0') &&
                    (time.charAt(1) == '?' || time.charAt(1) == loHour + '0')) {
                countHour++;
            }
        }
        for (int i = 0; i < 60; i++) {
            int hiMinute = i / 10;
            int loMinute = i % 10;
            if ((time.charAt(3) == '?' || time.charAt(3) == hiMinute + '0') &&
                    (time.charAt(4) == '?' || time.charAt(4) == loMinute + '0')) {
                countMinute++;
            }
        }
        return countHour * countMinute;
    }

}
