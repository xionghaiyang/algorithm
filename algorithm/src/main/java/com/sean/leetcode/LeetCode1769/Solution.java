package com.sean.leetcode.LeetCode1769;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-02 08:11
 * @Description: https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 * 1769. 移动所有球到每个盒子所需的最小操作数
 * 有 n 个盒子。
 * 给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
 * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。
 * 第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。
 * 注意，操作执行后，某些盒子中可能会存在不止一个小球。
 * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
 * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
 */
public class Solution {

    public int[] minOperations1(String boxes) {
        if (boxes == null || boxes.length() == 0) {
            return new int[0];
        }
        int n = boxes.length();
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i] += boxes.charAt(j) == '1' ? Math.abs(i - j) : 0;
            }
        }
        return answer;
    }

    public int[] minOperations2(String boxes) {
        int n = boxes.length();
        int[] left = new int[n];//left[i]表示将i左边所有的球移到i所需要的操作数
        int[] right = new int[n];//right[i]表示将i右边所有的球移到i所需要的操作数
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (boxes.charAt(i - 1) == '1') {
                count++;
            }
            left[i] = left[i - 1] + count;
        }
        count = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (boxes.charAt(i + 1) == '1') {
                count++;
            }
            right[i] = right[i + 1] + count;
        }
        //左边+右边
        for (int i = 0; i < n; i++) {
            left[i] += right[i];
        }
        return left;
    }

    public int[] minOperations(String boxes) {
        int left = boxes.charAt(0) - '0';
        int right = 0;
        int operations = 0;
        int n = boxes.length();
        for (int i = 1; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                operations += i;
            }
        }
        int[] res = new int[n];
        res[0] = operations;
        for (int i = 1; i < n; i++) {
            operations += left - right;
            if (boxes.charAt(i) == '1') {
                left++;
                right--;
            }
            res[i] = operations;
        }
        return res;
    }

}
