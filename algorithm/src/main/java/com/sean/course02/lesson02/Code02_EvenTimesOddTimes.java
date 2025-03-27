package com.sean.course02.lesson02;

/**
 * @Author xionghaiyang
 * @Date 2025-03-27 20:55
 * @Description 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 * 怎么把一个int类型的数，提取出最右侧的1来
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class Code02_EvenTimesOddTimes {

    //一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    //一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //因为a和b是两种数，所以eor (= a ^ b) !=0
        //提取出最右侧的1,-x=(~x)+1
        int rightOne = eor & (-eor);
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    //返回二进制数n中有多少个"1"
    public static int bit1counts(int n) {
        int res = 0;
        while (n != 0) {
            //-x = (~x)+1
            int rightOne = n & ((~n) + 1);
            res++;
            n ^= rightOne;
            //n -= rightOne;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);
        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);
    }

}
