package com.sean.leetcode.LeetCode670;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-13 08:35
 * @Description: https://leetcode.cn/problems/maximum-swap/
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 */
public class Solution {

    public int maximumSwap1(int num) {
        if (num == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            list.add(num % 10);
            num /= 10;
        }
        int n = list.size();
        for (int i = n - 1; i >= 1; i--) {
            int index = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (list.get(j) > list.get(i)) {
                    index = index == -1 ? j : (list.get(j) >= list.get(index) ? j : index);
                }
            }
            if (index != -1) {
                int temp = list.get(i);
                list.set(i, list.get(index));
                list.set(index, temp);
                break;
            }
        }
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            res = res * 10 + list.get(i);
        }
        return res;
    }

    public int maximumSwap(int num) {
        if (num == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            list.add(num % 10);
            num /= 10;
        }
        int n = list.size();
        int maxIndex = 0, index1 = -1, index2 = -1;
        for (int i = 0; i < n; i++) {
            if (list.get(i) > list.get(maxIndex)) {
                maxIndex = i;
            } else if (list.get(i) < list.get(maxIndex)) {
                index1 = i;
                index2 = maxIndex;
            }
        }
        if (index1 >= 0) {
            int temp = list.get(index1);
            list.set(index1, list.get(index2));
            list.set(index2, temp);
        }
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            res = res * 10 + list.get(i);
        }
        return res;
    }

}
