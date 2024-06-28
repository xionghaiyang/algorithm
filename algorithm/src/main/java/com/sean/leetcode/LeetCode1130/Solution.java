package com.sean.leetcode.LeetCode1130;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-31 08:04
 * @Description: https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/
 * 1130. 叶值的最小代价生成树
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 * 如果一个节点有 0 个子节点，那么该节点为叶节点。
 */
public class Solution {

    public int mctFromLeafValues1(int[] arr) {
        int n = arr.length;
        //dp[i][j]表示子数组[i][j]对应的所有子树非叶节点的最小总和
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //m[i][k]表示子数组[i][k]的最大值
        int[][] m = new int[n][n];
        for (int j = 0; j < n; j++) {
            m[j][j] = arr[j];
            dp[j][j] = 0;
            for (int i = j - 1; i >= 0; i--) {
                m[i][j] = Math.max(arr[i], m[i + 1][j]);
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + m[i][k] * m[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //问题转化:
    //给定一个数组arr,不断地合并相邻的数，合并代价为两个数的成绩，合并之后的数为两个数的最大值，
    //直到数组只剩一个数，求合并最小代价
    //假设一个数组arr[i](0<i<n-1)，满足arr[i-1]>=arr[i]且arr[i]<=arr[i+1],如果arr[i-1]<=arr[i+1],
    //那么优先将arr[i]和arr[i-1]合并是最优的，反之如果arr[i-1]>arr[i+1]，那么优先将arr[i]与arr[i+1]合并是最优的
    //单调栈算法（栈元素从底到顶是严格递减的），我们遍历数组arr，记当前遍历的值为x
    //如果栈非空且栈顶元素小于等于x,说明栈顶元素是符合前面所说的最优合并条件，将栈顶元素y出栈
    //如果栈空或栈顶元素大于x,那么将y与x合并，合并代价为x*y,合并之后值为x
    //否则将y与栈顶元素合并，合并代价为y与栈顶元素的成绩，合并之后的值为栈顶元素
    //重复以上过程直到栈空或栈顶元素大于x,然后将x入栈。
    //经过以上过程后，栈中的元素从底到顶是严格递减的，因此可以不断将栈顶的两个元素出栈合并，再入栈，直到栈元素数目小于2。返回最终代价和即可。
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int x : arr) {
            while (!stack.isEmpty() && stack.peek() <= x) {
                int y = stack.pop();
                if (stack.isEmpty() || stack.peek() > x) {
                    res += x * y;
                } else {
                    res += stack.peek() * y;
                }
            }
            stack.push(x);
        }
        while (stack.size() >= 2) {
            int x = stack.pop();
            res += x * stack.peek();
        }
        return res;
    }

}
