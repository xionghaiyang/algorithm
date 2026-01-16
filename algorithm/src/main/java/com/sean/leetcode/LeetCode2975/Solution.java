package com.sean.leetcode.LeetCode2975;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2026-01-16 16:10
 * @Description https://leetcode.cn/problems/maximum-square-area-by-removing-fences-from-a-field
 * 2975. 移除栅栏得到的正方形田地的最大面积
 * 有一个大型的 (m - 1) x (n - 1) 矩形田地，其两个对角分别是 (1, 1) 和 (m, n) ，田地内部有一些水平栅栏和垂直栅栏，分别由数组 hFences 和 vFences 给出。
 * 水平栅栏为坐标 (hFences[i], 1) 到 (hFences[i], n)，垂直栅栏为坐标 (1, vFences[i]) 到 (m, vFences[i]) 。
 * 返回通过 移除 一些栅栏（可能不移除）所能形成的最大面积的 正方形 田地的面积，或者如果无法形成正方形田地则返回 -1。
 * 由于答案可能很大，所以请返回结果对 10^9 + 7 取余 后的值。
 * 注意：田地外围两个水平栅栏（坐标 (1, 1) 到 (1, n) 和坐标 (m, 1) 到 (m, n) ）以及两个垂直栅栏（坐标 (1, 1) 到 (m, 1) 和坐标 (1, n) 到 (m, n) ）所包围。
 * 这些栅栏 不能 被移除。
 * 3 <= m, n <= 10^9
 * 1 <= hFences.length, vFences.length <= 600
 * 1 < hFences[i] < m
 * 1 < vFences[i] < n
 * hFences 和 vFences 中的元素是唯一的。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> hSet = f(hFences, m);
        Set<Integer> vSet = f(vFences, n);
        int res = 0;
        for (int x : hSet) {
            if (vSet.contains(x)) {
                res = Math.max(res, x);
            }
        }
        return res > 0 ? (int) ((long) res * res % MOD) : -1;
    }

    private Set<Integer> f(int[] arr, int max) {
        int n = arr.length;
        arr = Arrays.copyOf(arr, n + 2);
        arr[n++] = 1;
        arr[n++] = max;
        Arrays.sort(arr);
        //计算arr中任意两个数的差，保存到哈希集合中
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                set.add(arr[j] - arr[i]);
            }
        }
        return set;
    }

}
