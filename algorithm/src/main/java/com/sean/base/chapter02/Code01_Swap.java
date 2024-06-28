package com.sean.base.chapter02;

/**
 * @Author xionghaiyang
 * @Date 2022-08-13 09:46
 * @Description 如何不用额外变量交换两个数
 */
public class Code01_Swap {

    public static void main(String[] args) {
        int a = 16;
        int b = 603;
        System.out.println(a);
        System.out.println(b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);

        int[] arr = {3, 1, 100};
        int i = 0;
        int j = 0;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        System.out.println(arr[i] + "," + arr[j]);
    }

}
