package com.sean.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计数质数
 * https://leetcode-cn.com/problems/count-primes/
 */
public class LeetCode204 {

    //枚举--超出时间限制
    public static int countPrimes1(int n) {
        int res = 0;
        for (int i = 2; i < n; i++) {
            res += isPrime(i) ? 1 : 0;
        }
        return res;
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    //埃氏筛
    public static int countPrimes2(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                res += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return res;
    }

    //线性筛
    public static int countPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                isPrime[i * primes.get(j)] = 0;
                if(i % primes.get(j) == 0){
                    break;
                }
            }
        }
        return primes.size();
    }

}
