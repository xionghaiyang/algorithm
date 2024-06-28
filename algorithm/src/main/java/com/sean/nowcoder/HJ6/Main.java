package com.sean.nowcoder.HJ6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        for (long i = 2; i <= (long) Math.sqrt(num); i++) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "" : num);
    }

}
