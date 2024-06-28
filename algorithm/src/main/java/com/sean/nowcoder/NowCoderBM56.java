package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class NowCoderBM56 {

    public static void process(ArrayList<ArrayList<Integer>> ans, int[] num, ArrayList<Integer> temp, boolean[] visit) {
        if (temp.size() == num.length) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (visit[i]) {
                continue;
            }
            if (i > 0 && num[i - 1] == num[i] && visit[i-1]) {
                continue;
            }
            visit[i] = true;
            temp.add(num[i]);
            process(ans, num, temp, visit);
            visit[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        Arrays.sort(num);
        boolean[] visit = new boolean[num.length];
        Arrays.fill(visit, false);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        process(ans, num, temp, visit);
        return ans;
    }

}
