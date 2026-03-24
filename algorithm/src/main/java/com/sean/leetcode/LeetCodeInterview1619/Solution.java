package com.sean.leetcode.LeetCodeInterview1619;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 08:34
 * @Description https://leetcode.cn/problems/pond-sizes-lcci
 * 面试题 16.19. 水域大小
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
 * 若值为0则表示水域。
 * 由垂直、水平或对角连接的水域为池塘。
 * 池塘的大小是指相连接的水域的个数。
 * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 */
public class Solution {

    public int[] pondSizes(int[][] land) {
        int m = land.length, n = land[0].length;
        List<Integer> list = new ArrayList<>();
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    int size = 1;
                    land[i][j] = -1;
                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();
                        int x = arr[0], y = arr[1];
                        for (int[] dir : dirs) {
                            int nx = x + dir[0], ny = y + dir[1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && land[nx][ny] == 0) {
                                size++;
                                queue.offer(new int[]{nx, ny});
                                land[nx][ny] = -1;
                            }
                        }
                    }
                    list.add(size);
                }
            }
        }
        Collections.sort(list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
