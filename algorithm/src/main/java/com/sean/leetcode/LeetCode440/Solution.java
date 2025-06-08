package com.sean.leetcode.LeetCode440;

/**
 * @Author xionghaiyang
 * @Date 2025-06-09 06:31
 * @Description https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order
 * 440. 字典序的第K小数字
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * 1 <= k <= n <= 10^9
 */
public class Solution {

    //超时
    public int findKthNumber(int n, int k) {
        int res = 1;
        for (long num = 1; k > 0; k--) {
            res = (int) num;
            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return res;
    }

    public int findKthNumber1(int n, int k) {
        int node = 1;
        k--;//访问节点node
        while (k > 0) {
            int size = getSize(n, node);
            if (size <= k) {
                k -= size;
                node++; //跳过node子树，访问右侧兄弟节点，如果已经是最右节点，则会访问父节点的右侧兄弟节点
            } else {
                node *= 10; // 访问node的第一个儿子
                k--;
            }
        }
        return node;
    }

    //统计node子树大小
    private int getSize(int n, int node) {
        int size = 0;
        long left = node, right = node;
        while (left <= n) {
            size += Math.min(right, n) - left + 1;
            left *= 10;
            right = right * 10 + 9;
        }
        return size;
    }

}
