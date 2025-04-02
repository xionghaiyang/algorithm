package com.sean.course03.lesson03;

/**
 * @Author xionghaiyang
 * @Date 2025-04-02 22:46
 * @Description 蓄水池算法
 * 解决的问题：
 * 假设有一个源源吐出不同球的机器，
 * 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
 * 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里
 */
public class Code02_ReservoirSampling {

    public static class RandomBox {
        private int[] bag;
        private int N;
        private int count;

        public RandomBox(int capacity) {
            bag = new int[capacity];
            N = capacity;
            count = 0;
        }

        private int rand(int max) {
            return (int) (max * Math.random()) + 1;
        }

        public void add(int num) {
            count++;
            if (count <= N) {
                bag[count - 1] = num;
            } else {
                if (rand(count) <= N) {
                    bag[rand(N) - 1] = num;
                }
            }
        }

        public int[] choices() {
            int[] res = new int[N];
            for (int i = 0; i < N; i++) {
                res[i] = bag[i];
            }
            return res;
        }

    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] res = box.choices();
            for (int j = 0; j < res.length; j++) {
                counts[res[j]]++;
            }
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + "times:" + counts[i]);
        }
        System.out.println("测试结束");
    }

}
