package com.sean.leetcode.LeetCode1298;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-06-03 07:29
 * @Description https://leetcode.cn/problems/maximum-candies-you-can-get-from-boxes
 * 1298. 你能从盒子里获得的最大糖果数
 * 给你 n 个盒子，每个盒子的格式为 [status, candies, keys, containedBoxes] ，其中：
 * 状态字 status[i]：整数，如果 box[i] 是开的，那么是 1 ，否则是 0 。
 * 糖果数 candies[i]: 整数，表示 box[i] 中糖果的数目。
 * 钥匙 keys[i]：数组，表示你打开 box[i] 后，可以得到一些盒子的钥匙，每个元素分别为该钥匙对应盒子的下标。
 * 内含的盒子 containedBoxes[i]：整数，表示放在 box[i] 里的盒子所对应的下标。
 * 给你一个 initialBoxes 数组，表示你现在得到的盒子，你可以获得里面的糖果，
 * 也可以用盒子里的钥匙打开新的盒子，还可以继续探索从这个盒子里找到的其他盒子。
 * 请你按照上述规则，返回可以获得糖果的 最大数目 。
 * 1 <= status.length <= 1000
 * status.length == candies.length == keys.length == containedBoxes.length == n
 * status[i] 要么是 0 要么是 1 。
 * 1 <= candies[i] <= 1000
 * 0 <= keys[i].length <= status.length
 * 0 <= keys[i][j] < status.length
 * keys[i] 中的值都是互不相同的。
 * 0 <= containedBoxes[i].length <= status.length
 * 0 <= containedBoxes[i][j] < status.length
 * containedBoxes[i] 中的值都是互不相同的。
 * 每个盒子最多被一个盒子包含。
 * 0 <= initialBoxes.length <= status.length
 * 0 <= initialBoxes[i] < status.length
 */
public class Solution {

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        //获得的钥匙
        boolean[] getKeys = new boolean[n];
        //已经打开的盒子
        boolean[] openedBoxes = new boolean[n];
        boolean[] visited = new boolean[n];
        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int box : initialBoxes) {
            queue.offer(box);
        }
        while (!queue.isEmpty()) {
            int box = queue.poll();
            if (openedBoxes[box]) {//盒子已经打开过
                continue;
            }
            if (status[box] == 1 || getKeys[box]) {
                //获取盒子里面的糖果
                res += candies[box];
                //获取盒子里面的钥匙
                for (int key : keys[box]) {
                    getKeys[key] = true;
                }
                //获取盒子里面的盒子
                for (int containedBox : containedBoxes[box]) {
                    queue.offer(containedBox);
                }
                openedBoxes[box] = true;
            } else if (!visited[box]) {
                visited[box] = true;
                queue.offer(box);
            }
        }
        return res;
    }

    public int maxCandies1(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        //获得的盒子
        boolean[] getBoxes = new boolean[n];
        //获得的钥匙
        boolean[] getKeys = new boolean[n];
        //已经打开的盒子
        boolean[] openedBoxed = new boolean[n];
        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int box : initialBoxes) {
            getBoxes[box] = true;
            if (status[box] == 1) {//可以打开
                queue.offer(box);
            }
        }
        while (!queue.isEmpty()) {
            int box = queue.poll();
            if (openedBoxed[box]) {//已经打开过
                continue;
            }
            //获取盒子里面的糖果
            res += candies[box];
            //获取盒子里面的钥匙
            for (int key : keys[box]) {
                getKeys[key] = true;
                if (getBoxes[key] && !openedBoxed[key]) {
                    queue.offer(key);
                }
            }
            //获取盒子里面的盒子
            for (int containedBox : containedBoxes[box]) {
                getBoxes[containedBox] = true;
                if (status[containedBox] == 1 || getKeys[containedBox]) {
                    queue.offer(containedBox);
                }
            }
            openedBoxed[box] = true;
        }
        return res;
    }

}
