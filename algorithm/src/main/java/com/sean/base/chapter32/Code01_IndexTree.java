package com.sean.base.chapter32;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-02 19:18
 * @Description: TODO
 */
public class Code01_IndexTree {

    //下标从1开始
    public static class IndexTree {
        private int[] tree;
        private int N;

        //0位置弃而不用
        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        //1~index累加和是多少
        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += index & -index;
            }
        }

    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int res = 0;
            for (int i = 1; i <= index; i++) {
                res += nums[i];
            }
            return res;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        System.out.println("test finish");
    }

}
