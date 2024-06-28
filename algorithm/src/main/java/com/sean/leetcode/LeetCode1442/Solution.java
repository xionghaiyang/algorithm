package com.sean.leetcode.LeetCode1442;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-24 12:30
 * @Description https://leetcode.cn/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 * 1442. 形成两个异或相等数组的三元组数目
 * 给你一个整数数组 arr 。
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 */
public class Solution {

    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ arr[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (s[i] == s[k + 1]) {
                    res += k - i;
                }
            }
        }
        return res;
    }

    public int countTriplets1(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ arr[i];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> total = new HashMap<>();
        int res = 0;
        for (int k = 0; k < n; k++) {
            if (cnt.containsKey(s[k + 1])) {
                res += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
            }
            cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
            total.put(s[k], total.getOrDefault(s[k], 0) + k);
        }
        return res;
    }

    public int countTriplets2(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> total = new HashMap<>();
        int res = 0, s = 0;
        for (int k = 0; k < n; k++) {
            int val = arr[k];
            if (cnt.containsKey(s ^ val)) {
                res += cnt.get(s ^ val) * k - total.get(s ^ val);
            }
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
            total.put(s, total.getOrDefault(s, 0) + k);
            s ^= val;
        }
        return res;
    }

}
