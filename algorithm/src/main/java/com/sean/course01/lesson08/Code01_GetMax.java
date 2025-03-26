package com.sean.course01.lesson08;

/**
 * @Author xionghaiyang
 * @Date 2025-03-26 18:12
 * @Description 不要用任何比较判断, 返回两个数中较大的数
 */
public class Code01_GetMax {

    //0变1，1变0
    private static int flip(int n) {
        return n ^ 1;
    }

    //1为非负，0为负
    private static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    public static int getMax(int a, int b) {
        int c = a - b;
        int selectA = sign(c);
        int selectB = flip(selectA);
        return a * selectA + b * selectB;
    }

    public static int getMax1(int a, int b) {
        //获取a和b的符号位,1为非负数，0为负数
        int sa = sign(a);
        int sb = sign(b);
        //a和b的符号不同为1，否则为0
        int difSab = sa ^ sb;
        int c = a - b;
        //差值符号，1表示a>=b,0表示a<b
        int sc = sign(c);
        int selectA = (difSab & sa) | (flip(difSab) & sc);
        int selectB = flip(selectA);
        return a * selectA + b * selectB;
    }

    public static void main(String[] args) {
        int a = -16;
        int b = -19;
        System.out.println(getMax(a, b));
        System.out.println(getMax1(a, b));
        a = 2147483647;
        b = -2147480000;
        System.out.println(getMax(a, b));//错误的答案因为溢出
        System.out.println(getMax1(a, b));
    }

}
