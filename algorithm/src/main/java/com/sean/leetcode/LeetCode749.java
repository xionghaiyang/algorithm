package com.sean.leetcode;

import java.util.*;

public class LeetCode749 {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int containVirus(int[][] isInfected) {
        int m = isInfected.length, n = isInfected[0].length;
        int res = 0;
        while (true) {
            List<Set<Integer>> neighbors = new ArrayList<>();
            List<Integer> firewalls = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1) {
                        Queue<int[]> queue = new ArrayDeque<>();
                        queue.offer(new int[]{i, j});
                        Set<Integer> neighbor = new HashSet<>();
                        int firewall = 0, index = neighbors.size() + 1;
                        isInfected[i][j] = -index;
                        while (!queue.isEmpty()) {
                            int[] arr = queue.poll();
                            int x = arr[0], y = arr[1];
                            for (int k = 0; k < 4; k++) {
                                int nx = x + dirs[k][0], ny = y + dirs[k][1];
                                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                    if (isInfected[nx][ny] == 1) {
                                        queue.offer(new int[]{nx, ny});
                                        isInfected[nx][ny] = -index;
                                    } else if (isInfected[nx][ny] == 0) {
                                        firewall++;
                                        neighbor.add(getHash(nx, ny));
                                    }
                                }
                            }
                        }
                        neighbors.add(neighbor);
                        firewalls.add(firewall);
                    }
                }
            }
            if (neighbors.isEmpty()) {
                break;
            }
            int index = 0;
            for (int i = 1; i < neighbors.size(); i++) {
                if (neighbors.get(i).size() > neighbors.get(index).size()) {
                    index = i;
                }
            }
            res += firewalls.get(index);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] < 0) {
                        if (isInfected[i][j] != -index - 1) {
                            isInfected[i][j] = 1;
                        } else {
                            isInfected[i][j] = 2;
                        }
                    }
                }
            }
            for (int i = 0; i < neighbors.size(); i++) {
                if (i != index) {
                    for (int val : neighbors.get(i)) {
                        int x = val >> 16, y = val & ((1 << 16) - 1);
                        isInfected[x][y] = 1;
                    }
                }
            }
            if (neighbors.size() == 1) {
                break;
            }
        }
        return res;
    }

    public int getHash(int x, int y) {
        return (x << 16) ^ y;
    }

}
