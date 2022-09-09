package com.sean.leetcode.LeetCode733;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 19:12
 * @Description: https://leetcode.cn/problems/flood-fill/
 * 733. 图像渲染
 * 有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。
 * 你也被给予三个整数 sr ,  sc 和 newColor 。你应该从像素 image[sr][sc] 开始对图像进行 上色填充 。
 * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，
 * 接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。
 * 将所有有记录的像素点的颜色值改为 newColor 。
 * 最后返回 经过上色渲染后的图像 。
 */
public class Solution {

    int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    public int[][] floodFill1(int[][] image, int sr, int sc, int color) {
        int curColor = image[sr][sc];
        if (curColor == color) {
            return image;
        }
        int m = image.length, n = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = color;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = cur[0] + dir[i][0];
                int y = cur[1] + dir[i][1];
                if (x >= 0 && x < m && y >= 0 && y < n && image[x][y] == curColor) {
                    queue.offer(new int[]{x, y});
                    image[x][y] = color;
                }
            }
        }
        return image;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int curColor = image[sr][sc];
        if (curColor != color) {
            dfs(image, sr, sc, curColor, color);
        }
        return image;
    }

    public void dfs(int[][] image, int x, int y, int curColor, int color) {
        if (image[x][y] == curColor) {
            image[x][y] = color;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx >= 0 && nx < image.length && ny >= 0 && ny < image[0].length) {
                    dfs(image, nx, ny, curColor, color);
                }
            }
        }
    }

}
