package com.sean.learning01.class01;

public class Code01_PrintBinary {

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int a = Integer.MIN_VALUE;
        print(a);
        //带符号右移
        print(a >> 1);
        //不带符号右移
        print(a >>> 1);

        int b = 5;
        int c = -b;
        int d = ~b + 1;

        System.out.println(c);
        System.out.println(d);

        int x1 = 1231243;
        int x2 = 12324324;
        print(x1);
        print(x2);
        print(x1 | x2);
        print(x1 & x2);
        print(x1 ^ x2);
    }

}
