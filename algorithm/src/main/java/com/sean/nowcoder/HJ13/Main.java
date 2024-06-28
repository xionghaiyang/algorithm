package com.sean.nowcoder.HJ13;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] words = line.split(" ");
            for (int i = words.length - 1; i >= 0; i--) {
                stringBuilder.append(words[i]);
                if (i != 0) {
                    stringBuilder.append(" ");
                }
            }
            System.out.println(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
    }
}
