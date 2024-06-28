package com.sean.lintcode.LintCode2878;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-19 11:51
 */
public class Main {

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            PrintStream ps = new PrintStream(outputPath);

            // get data
            String str = in.nextLine();
            String strArr[] = str.substring(str.indexOf("[") + 1, str.indexOf("]")).split(",");
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < strArr.length; i++) {
                String num = strArr[i].trim();
                list.add(Integer.parseInt(num));
            }

            Solution solution = new Solution();
            List<Integer> rs = solution.removeNumber(list);

            Collections.sort(rs);

            StringBuilder sb = new StringBuilder("[");
            for (int num : rs) {
                sb.append(num + ", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]");
            ps.print(sb.toString());
            ps.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
