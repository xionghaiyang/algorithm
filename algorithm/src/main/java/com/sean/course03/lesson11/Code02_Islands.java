package com.sean.course03.lesson11;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-12-10 20:07
 * @Description 岛问题
 * 一个只有0和1两种数字的二维矩阵中，
 * 上下左右能练成一片的1，算一个岛
 * 返回矩阵中，一共有几个岛
 */
public class Code02_Islands {

    public static int countIslands1(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    infect(matrix, i, j, N, M);
                }
            }
        }
        return res;
    }

    private static void infect(int[][] matrix, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || matrix[i][j] != 1) {
            return;
        }
        matrix[i][j] = 2;
        infect(matrix, i + 1, j, N, M);
        infect(matrix, i - 1, j, N, M);
        infect(matrix, i, j + 1, N, M);
        infect(matrix, i, j - 1, N, M);
    }

    public static class Element<K> {
        public K value;

        public Element(K value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<K> {
        private Map<K, Element<K>> elementMap;
        private Map<Element<K>, Element<K>> fatherMap;
        private Map<Element<K>, Integer> sizeMap;

        public UnionFindSet(List<K> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (K value : list) {
                Element<K> element = new Element<>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        private Element<K> findHead(Element<K> element) {
            Stack<Element<K>> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        public boolean isSameSet(K a, K b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(K a, K b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<K> aF = findHead(elementMap.get(a));
                Element<K> bF = findHead(elementMap.get(b));
                if (aF != bF) {
                    Element<K> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<K> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }

        public int getSetNum() {
            return sizeMap.size();
        }
    }

    public static int countIslands2(int[][] matrix) {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    String position = row + "_" + col;
                    list.add(position);
                }
            }
        }
        UnionFindSet<String> unionFindSet = new UnionFindSet<>(list);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    String position = row + "_" + col;
                    if (row - 1 >= 0 && matrix[row - 1][col] == 1) {
                        String up = (row - 1) + "_" + col;
                        unionFindSet.union(up, position);
                    }
                    if (col - 1 >= 0 && matrix[row][col - 1] == 1) {
                        String left = row + "_" + (col - 1);
                        unionFindSet.union(left, position);
                    }
                }
            }
        }
        return unionFindSet.getSetNum();
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0}, {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands1(m1));

        int[][] m1Other = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0}, {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands2(m1Other));

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands1(m2));

        int[][] m2Other = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};

        System.out.println(countIslands2(m2Other));
    }

}
