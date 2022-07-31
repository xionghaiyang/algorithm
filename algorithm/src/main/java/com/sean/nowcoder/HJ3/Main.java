package com.sean.nowcoder.HJ3;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        while (sc.hasNext()) {
            int N = sc.nextInt();
            for (int i = 0; i < N; i++) {
                int num = sc.nextInt();
                set.add(num);
            }
            Iterator<Integer> iter = set.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
            set.clear();
        }
    }

}
