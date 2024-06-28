package com.sean.base.chapter15;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-31 22:10
 * @Description: TODO
 */
public class Code01_FriendCircles {

    public int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) {//i和j互相认识
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    public class UnionFind {
        //parent[i] = k: i的父亲是k
        private int[] parent;
        //size[i] = k：如果i是代表节点,size[i]才有意义，否则无意义
        //i所在集合的大小是多少
        private int[] size;
        //辅助结构
        private int[] help;
        //一共有多少个集合
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        //从i开始一直往上，往上到不能再往上，代表节点返回
        //这个过程要做路径压缩
        private int find(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = i;
            }
            return i;
        }

        private void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = parent[f1];
                } else {
                    size[f2] += size[f1];
                    parent[f1] = parent[f2];
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }

    }
}
