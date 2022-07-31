package com.sean;

public class UnionFind {
    private int[] parent;
    private int[] size;
    private int[] help;
    private int sets;

    UnionFind(int N) {
        parent = new int[N];
        size = new int[N];
        help = new int[N];
        sets = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int i) {
        int t = 0;
        while (i != parent[i]) {
            help[t++] = i;
            i = parent[i];
        }
        for (t--; t >= 0; t--) {
            parent[help[t]] = i;
        }
        return i;
    }

    void union(int i, int j) {
        int f1 = find(i);
        int f2 = find(j);
        if (f1 != f2) {
            if (size[f1] >= size[f2]) {
                size[f1] += size[f2];
                parent[f2] = f1;
            } else {
                size[f2] += size[f1];
                parent[f1] = f2;
            }
            sets--;
        }
    }

    int sets() {
        return sets;
    }

}


