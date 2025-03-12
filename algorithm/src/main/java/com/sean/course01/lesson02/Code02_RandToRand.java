package com.sean.course01.lesson02;

/**
 * @Author xionghaiyang
 * @Date 2025-03-12 14:41
 * @Description 随机数
 */
public class Code02_RandToRand {

    //返回[0,1)上的一个小数
    //任意的x,x属于[0,1),[0,x)范围上的数出现的概率由原来的x调整成x的平方
    public static double xToXpower2() {
        //P(max(X,Y)<=x) = P(X<=x)*P(Y<=x) = x*x
        return Math.max(Math.random(), Math.random());
    }

    //返回[0,1)上的一个小数
    //任意的x,x属于[0,1),[0,x)范围上的数出现的概率由原来的x调整成 1-(1-x)^2
    public static double x2X() {
        //P(min(X,Y)<=x) = 1 - P(X>x,Y>x) = 1 - P(X>x)P(Y>x) = 1 - (1-x)^2
        return Math.min(Math.random(), Math.random());
    }

    //你只能知道，f会以固定概率返回0和1，但是f的内容，你看不到
    private static int f() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    //等概率返回0和1
    public static int f1() {
        int res = 0;
        do {
            res = f();
        } while (res == f());
        return res;
    }

    //此函数只能使用，不能修改
    //等概率返回1~5
    private static int g() {
        return (int) (Math.random() * 5) + 1;
    }

    //随机机制，只能用g
    //等概率得到0和1
    private static int g1() {
        int res = 0;
        do {
            res = g();
        } while (res == 3);
        return res < 3 ? 0 : 1;
    }

    //得到000~111做到等概率0~7等概率返回一个
    private static int g2() {
        return (g1() << 2) + (g1() << 1) + g1();
    }

    //0~6等概率返回一个
    private static int g3() {
        int res = 0;
        do {
            res = g2();
        } while (res == 7);
        return res;
    }

    //等概率返回1~7
    private static int g4() {
        return g3() + 1;
    }

    //这个结构是唯一的随机机制
    //你只能初始化并使用，不可修改
    public static class RandomBox {
        private final int min;
        private final int max;

        //初始化时请一定不要让min == max
        public RandomBox(int min, int max) {
            this.min = max;
            this.max = max;
        }

        public int min() {
            return min;
        }

        public int max() {
            return max;
        }

        //13~17
        //13 + [0,4]
        public int random() {
            return min + (int) (Math.random() * (max - min + 1));
        }
    }

    //利用条件RandomBox，如何等概率返回0和1
    public static int rand01(RandomBox randomBox) {
        int min = randomBox.min();
        int max = randomBox.max();
        //min~max
        int size = max - min + 1;
        //size是不是奇数，odd奇数
        boolean odd = (size & 1) != 0;
        int mid = size / 2;
        int res = 0;
        do {
            res = randomBox.random() - min;
        } while (odd && res == mid);
        return res < mid ? 0 : 1;
    }

    //给你一个RandomBox,这是唯一能借助的随机机制
    //等概率返回from~to范围上任何一个数
    //要求from<=to
    public static int random(RandomBox randomBox, int from, int to) {
        if (from == to) {
            return from;
        }
        int range = to - from;
        int num = 1;
        //求0~range需要几个2进制位
        while ((1 << num) - 1 < range) {
            num++;
        }
        int res = 0;
        do {
            res = 0;
            for (int i = 0; i < num; i++) {
                res |= (rand01(randomBox) << i);
            }
        } while (res > range);
        return res + from;
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        //Math.random()->double->[0,1)
        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.75) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        System.out.println("============================");
        //[0,1)->[0,8)
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println((double) 5 / (double) 8);

        System.out.println("=========================");
        int K = 9;
        //[0,K)->[0,K-1]
        int[] counts = new int[K];
        for (int i = 0; i < testTimes; i++) {
            counts[(int) (Math.random() * K)]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数，出现了" + counts[i] + "次");
        }

        System.out.println("====================");
        count = 0;
        double x = 0.17;
        for (int i = 0; i < testTimes; i++) {
            if (xToXpower2() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));

        System.out.println("====================");
        count = 0;
        x = 0.17;
        for (int i = 0; i < testTimes; i++) {
            if (x2X() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println((double) 1 - Math.pow((double) 1 - x, 2));

        System.out.println("==========");
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f1() == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("==========");

        counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int num = g4();
            counts[num]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
        System.out.println("测试结束");
    }

}
