package com.sean.nowcoder.HJ1;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] words = str.split(" ");
        System.out.println(words[words.length - 1].length());
    }

}
