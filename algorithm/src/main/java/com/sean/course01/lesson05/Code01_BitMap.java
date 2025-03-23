package com.sean.course01.lesson05;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-03-23 20:06
 * @Description 位图
 */
public class Code01_BitMap {

    public static class BitMap {
        //long有64位，每一位可以代表一个数是否存在（0或者1）
        private long[] bits;

        public BitMap(int max) {
            //>>6相当于除以64（2的6次方）
            bits = new long[(max + 64) >> 6];
        }

        public void add(int num) {
            //63(10) = 111111(2)
            //num & 111111(2) = num % 64
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num) {
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("测试开始！");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        Set<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) ((max + 1) * Math.random());
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("出错了！");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束！");
    }

}
