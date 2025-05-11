package com.sean.leetcode.LeetCode2094;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-05-12 05:28
 * @Description https://leetcode.cn/problems/finding-3-digit-even-numbers
 * 2094. 找出 3 位偶数
 * 给你一个整数数组 digits ，其中每个元素是一个数字（0 - 9）。
 * 数组中可能存在重复元素。
 * 你需要找出 所有 满足下述条件且 互不相同 的整数：
 * 该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。
 * 该整数不含 前导零
 * 该整数是一个 偶数
 * 例如，给定的 digits 是 [1, 2, 3] ，整数 132 和 312 满足上面列出的全部条件。
 * 将找出的所有互不相同的整数按 递增顺序 排列，并以数组形式返回。
 * 3 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class Solution {

    List<Integer> list = new ArrayList<>();

    public int[] findEvenNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int digit : digits) {
            cnt[digit]++;
        }
        process(cnt, 0, 0);
        int size = list.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void process(int[] cnt, int i, int num) {
        if (i == 3) {
            list.add(num);
            return;
        }
        for (int j = 0; j < 10; j++) {
            if (cnt[j] > 0 && ((i == 0 && j > 0) || i == 1 || (i == 2 && (j & 1) == 0))) {
                cnt[j]--;
                process(cnt, i + 1, num * 10 + j);
                cnt[j]++;
            }
        }
    }

}
