package com.sean.base.chapter29;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-29 20:45
 * @Description: TODO
 */
public class Code03_ReservoirSampling {

    public static class RandomBox {
        private int[] bag;
        private int N;
        private int count = 0;

        public RandomBox(int capacity) {
            bag = new int[capacity];
            N = capacity;
            count = 0;
        }

        private int rand(int max) {
            return (int) (Math.random() * max) + 1;
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

    //请等概率返回1~i中的一个数字
    public int random(int i) {
        return (int) (Math.random() * i) + 1;
    }

    public static void main(String[] args) {
        Code03_ReservoirSampling solution = new Code03_ReservoirSampling();
        System.out.println("hello");
        int test = 10000;
        int ballNum = 17;
        int[] count = new int[ballNum + 1];
        for (int i = 0; i < test; i++) {
            int[] bag = new int[10];
            int bagi = 0;
            for (int num = 1; num <= ballNum; num++) {
                if (num <= 10) {
                    bag[bagi++] = num;
                } else {
                    if (solution.random(num) <= 10) {
                        bagi = (int) (Math.random() * 10);
                        bag[bagi] = num;
                    }
                }
            }
            for (int num : bag) {
                count[num]++;
            }
        }
        for (int i = 0; i <= ballNum; i++) {
            System.out.println(count[i]);
        }

        System.out.println("hello");
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
            RandomBox randomBox = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                randomBox.add(num);
            }
            int[] res = randomBox.choices();
            for (int j = 0; j < res.length; j++) {
                counts[res[j]]++;
            }
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times: " + counts[i]);
        }
    }

}
