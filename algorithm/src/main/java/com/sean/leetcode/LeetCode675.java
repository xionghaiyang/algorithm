package com.sean.leetcode;


import java.util.*;

public class LeetCode675 {

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        int row = forest.size();
        int col = forest.get(0).size();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        Collections.sort(trees, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return forest.get(o1[0]).get(o1[1]) - forest.get(o2[0]).get(o2[1]);
            }
        });
        int cx = 0;
        int cy = 0;
        int res = 0;
        for (int i = 0; i < trees.size(); i++) {
            int steps = bfs(forest, cx, cy, trees.get(i)[0], trees.get(i)[1]);
            if (steps == -1) {
                return -1;
            }
            res += steps;
            cx = trees.get(i)[0];
            cy = trees.get(i)[1];
        }
        return res;
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return 0;
        }
        int row = forest.size();
        int col = forest.get(0).size();
        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visted = new boolean[row][col];
        queue.offer(new int[]{sx, sy});
        visted[sx][sy] = true;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] cell = queue.poll();
                int cx = cell[0], cy = cell[1];
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dirs[j][0];
                    int ny = cy + dirs[j][1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                        if (!visted[nx][ny] && forest.get(nx).get(ny) > 0) {
                            if (nx == tx && ny == ty) {
                                return step;
                            }
                            queue.offer(new int[]{nx, ny});
                            visted[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

}
