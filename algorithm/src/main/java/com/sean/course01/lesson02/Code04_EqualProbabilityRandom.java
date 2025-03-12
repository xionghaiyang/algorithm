package com.sean.course01.lesson02;

/**
 * @Author xionghaiyang
 * @Date 2025-03-12 18:13
 * @Description 等概率随机
 */
public class Code04_EqualProbabilityRandom {

    //内部内容不可见
    private static int f() {
        return Math.random() < 0.8 ? 0 : 1;
    }

    //等概率返回0和1
    public static int g() {
        int first = 0;
        do {
            first = f();
        } while (first == f());
        return first;
    }

    //这个结构是唯一的随机机制
    //你只能初始化并使用，不可修改
    public static class RandomBox {
        private final double p;

        //初始化请一定满足0<p<1
        public RandomBox(double p) {
            this.p = p;
        }

        public int random() {
            return Math.random() < p ? 0 : 1;
        }
    }

    //底层依赖一个以p概率返回0，以1-p概率返回1的随机函数rand01
    public static int rand01(RandomBox randomBox) {
        int num;
        do {
            num = randomBox.random();
        } while (num == randomBox.random());
        return num;
    }

    public static void main(String[] args) {
        int testTimes = 10000000;
        int[] counts = new int[2];
        for (int i = 0; i < testTimes; i++) {
            counts[g()]++;
        }
        System.out.println(counts[0] + "," + counts[1]);

        double p = 0.88;
        RandomBox randomBox = new RandomBox(p);
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (rand01(randomBox) == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
    }

}
