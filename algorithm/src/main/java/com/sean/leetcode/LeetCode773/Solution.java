package com.sean.leetcode.LeetCode773;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-19 17:38
 * @Description: https://leetcode.cn/problems/sliding-puzzle/description/
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。
 * 一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 */
public class Solution {

    private int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        String initial = sb.toString();
        if ("123450".equals(initial)) {
            return 0;
        }
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(initial);
        Set<String> set = new HashSet<>();
        set.add(initial);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String nextStatus : getNextStatus(status)) {
                    if (!set.contains(nextStatus)) {
                        if ("123450".equals(nextStatus)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        set.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    //枚举status通过一次变换操作得到的状态
    private List<String> getNextStatus(String status) {
        List<String> res = new ArrayList<>();
        char[] arr = status.toCharArray();
        int x = status.indexOf('0');
        for (int y : neighbors[x]) {
            swap(arr, x, y);
            res.add(new String(arr));
            swap(arr, x, y);
        }
        return res;
    }

    private void swap(char[] arr, int x, int y) {
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
