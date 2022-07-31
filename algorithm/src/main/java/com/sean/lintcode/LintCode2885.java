package com.sean.lintcode;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;

public class LintCode2885 {

    public static class Solution {

        public List<String> sortName(List<String> list) {
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareToIgnoreCase(o2);
                }
            });
            return list;
        }

    }

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            PrintStream ps = new PrintStream(outputPath);

            List<String> list = new ArrayList<>();
            while (in.hasNextLine()) {
                String str = in.nextLine();
                list.add(str.trim());
            }
            Solution solution = new Solution();
            list = solution.sortName(list);

            for (String str : list) {
                ps.println(str);
            }
            ps.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
