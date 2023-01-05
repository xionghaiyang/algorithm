package com.sean.leetcode.LeetCode1803;


/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-05 09:22
 * @Description: https://leetcode.cn/problems/count-pairs-with-xor-in-a-range/
 * 1803. 统计异或值在范围内的数对有多少
 * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
 * 漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
 */
public class Solution {

    class Trie {
        Trie[] son;
        int sum = 0;

        public Trie() {
            sum = 0;
            son = new Trie[2]; //son[0]表示左子树，son[1]表示右子树
        }
    }

    //字典树的根节点
    private Trie root = null;
    //最高位的二进制位编号为14
    private int high_bit = 14;

    public int countPairs(int[] nums, int low, int high) {
        return f(nums, high) - f(nums, low - 1);
    }

    private int f(int[] nums, int x) {
        root = new Trie();
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            add(nums[i - 1]);
            res += get(nums[i], x);
        }
        return res;
    }

    private void add(int num) {
        Trie cur = root;
        for (int k = high_bit; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (cur.son[bit] == null) {
                cur.son[bit] = new Trie();
            }
            cur = cur.son[bit];
            cur.sum++;
        }
    }

    private int get(int num, int x) {
        Trie cur = root;
        int sum = 0;
        for (int k = high_bit; k >= 0; k--) {
            int r = (num >> k) & 1;
            if (((x >> k) & 1) != 0) {
                if (cur.son[r] != null) {
                    sum += cur.son[r].sum;
                }
                if (cur.son[r ^ 1] == null) {
                    return sum;
                }
                cur = cur.son[r ^ 1];
            } else {
                if (cur.son[r] == null) {
                    return sum;
                }
                cur = cur.son[r];
            }
        }
        sum += cur.sum;
        return sum;
    }

}
