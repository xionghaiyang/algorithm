package com.sean.interview.adecco.question1;

import java.util.*;

public class Main {

    private static List<Integer> temp = new ArrayList<>();
    private static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        String S = sc.next();
        String[] strs = S.split("-");
        int m = strs.length;
        Map<Integer, Info> map = new HashMap<>();
        for (int i = 1; i < m; i++) {
            String str = strs[i];
            int len = str.length(), big = 0;
            for (int j = 0; j < len; j++) {
                if (Character.isUpperCase(str.charAt(j))) {
                    big++;
                }
            }
            map.put(i, new Info(str, big, len - big));
        }
        dfs(1, m - 1, K);
        StringBuilder ret = new StringBuilder();
        for (List<Integer> list : res) {
            int count = 0, big = 0, small = 0;
            for (int index : list) {
                Info info = map.get(index);
                big += info.getBig();
                small += info.getSmall();
                count++;
                ret.append(info.getStr());
                if (count != K) {
                    ret.append("-");
                }
            }
            if (big >= small) {
                System.out.println(ret.toString().toUpperCase());
            } else {
                System.out.println(ret.toString().toLowerCase());
            }
            ret.setLength(0);
        }
    }

    private static void dfs(int cur, int m, int k) {
        if (temp.size() + m - cur + 1 < k) {
            return;
        }
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(cur);
        dfs(cur + 1, m, k);
        temp.remove(temp.size() - 1);
        dfs(cur + 1, m, k);
    }

}

class Info {
    private String str;
    private int big;
    private int small;

    public Info(String str, int big, int small) {
        this.str = str;
        this.big = big;
        this.small = small;
    }

    public String getStr() {
        return str;
    }

    public int getBig() {
        return big;
    }

    public int getSmall() {
        return small;
    }
}
