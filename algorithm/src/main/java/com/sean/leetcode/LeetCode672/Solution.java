package com.sean.leetcode.LeetCode672;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-15 11:31
 * @Description: https://leetcode.cn/problems/bulb-switcher-ii/
 * 672. 灯泡开关 Ⅱ
 * 房间中有 n 只已经打开的灯泡，编号从 1 到 n 。墙上挂着 4 个开关 。
 * 这 4 个开关各自都具有不同的功能，其中：
 * 开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
 * 开关 2 ：反转编号为偶数的灯的状态（即 2, 4, ...）
 * 开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
 * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
 * 你必须 恰好 按压开关 presses 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。
 * 给你两个整数 n 和 presses ，执行完所有按压之后，返回 不同可能状态 的数量。
 */
public class Solution {

    public int flipLights(int n, int presses) {
        Set<Integer> set = new HashSet<>();
        //共1<<4种情况
        for (int i = 0; i < 1 << 4; i++) {
            //4个开关的状态
            int[] pressArr = new int[4];
            for (int j = 0; j < 4; j++) {
                pressArr[j] = (i >> j) & 1;
            }
            int sum = Arrays.stream(pressArr).sum();
            if (sum % 2 == presses % 2 && sum <= presses) {
                //编号为 6k+1受按钮 1,3,4影响；
                int status = pressArr[0] ^ pressArr[2] ^ pressArr[3];
                //编号为 6k,6k+2受按钮 1,2 影响；
                if (n >= 2) {
                    status |= (pressArr[0] ^ pressArr[1]) << 1;
                }
                //编号为 6k+3, 6k+5,受按钮 1,3影响；
                if (n >= 3) {
                    status |= (pressArr[0] ^ pressArr[2]) << 2;
                }
                //编号为 6k+4,受按钮 1,2,4 影响。
                if (n >= 4) {
                    status |= (pressArr[0] ^ pressArr[1] ^ pressArr[3]) << 3;
                }
                set.add(status);
            }
        }
        return set.size();
    }

}
