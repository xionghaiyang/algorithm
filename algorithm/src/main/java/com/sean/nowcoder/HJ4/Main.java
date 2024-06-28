package com.sean.nowcoder.HJ4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer stringBuffer = new StringBuffer();
        while (sc.hasNext()) {
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                stringBuffer.append(str.charAt(i));
                if (stringBuffer.length() == 8) {
                    System.out.println(stringBuffer.toString());
                    stringBuffer.setLength(0);
                }
            }
            int len = stringBuffer.length();
            if (len != 0) {
                for (int i = len; i < 8; i++) {
                    stringBuffer.append("0");
                }
                System.out.println(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

}
