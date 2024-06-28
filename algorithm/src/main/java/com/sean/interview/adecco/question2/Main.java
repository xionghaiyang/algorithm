package com.sean.interview.adecco.question2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int k = sc.nextInt();
        int n = str.length();
        k = k > n ? n : k;
        PriorityQueue<Info> infos = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if (o1.getCharacter() != o2.getCharacter()) {
                    return o2.getCharacter() - o1.getCharacter();
                } else {
                    return o2.getIndex() - o1.getIndex();
                }
            }
        });
        for (int i = 0; i < n; i++) {
            if (infos.size() < k) {
                infos.add(new Info(str.charAt(i), i));
            } else {
                infos.poll();
                infos.add(new Info(str.charAt(i), i));
            }
        }
        System.out.println(infos.peek().getIndex());
    }

}

class Info {
    private Character character;
    private int index;

    public Info(Character character, int index) {
        this.character = character;
        this.index = index;
    }

    public Character getCharacter() {
        return character;
    }

    public int getIndex() {
        return index;
    }
}
