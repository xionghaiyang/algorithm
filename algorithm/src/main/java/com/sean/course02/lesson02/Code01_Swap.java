package com.sean.course02.lesson02;

/**
 * @Author xionghaiyang
 * @Date 2025-03-27 20:42
 * @Description 如何不用额外变量交换两个数
 */
public class Code01_Swap {

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

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
        swap(arr, 0, 0);
        //0^N==N   N^N==0
        System.out.println(arr[0] + "," + arr[1] + "," + arr[2]);
    }

}
