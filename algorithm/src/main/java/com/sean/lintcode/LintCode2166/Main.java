package com.sean.lintcode.LintCode2166;

import java.io.*;
import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 08:46
 */
public class Main {

    public static void main(String[] args) {
        try {
            String inputDir = args[0];
            String outputDir = args[1];

            if (!outputDir.endsWith("/")) {
                outputDir = outputDir + "/";
            }

            List<String> inputFilePaths = new ArrayList<>();
            File[] inputFiles = new File(inputDir).listFiles();
            for (File file : inputFiles) {
                if (file.getPath().endsWith(".in")) {
                    inputFilePaths.add(file.getPath());
                }
            }

            Collections.sort(inputFilePaths, new Comparator<String>() {
                @Override
                public int compare(String f1, String f2) {
                    int f1num = Integer.parseInt(new File(f1).getName()
                            .replace(".in", ""));
                    int f2num = Integer.parseInt(new File(f2).getName()
                            .replace(".in", ""));
                    return f1num - f2num;
                }
            });

            for (String inputFilePath : inputFilePaths) {
                Scanner in = new Scanner(new FileReader(inputFilePath));
                String outputFilename = new File(inputFilePath).getName()
                        .replace(".in", ".out");
                PrintWriter writer = new PrintWriter(outputDir + outputFilename,
                        "UTF-8");
                String stdoutPath = outputDir + outputFilename.replace(".out",
                        ".stdout");
                PrintStream stdout = new PrintStream(stdoutPath);
                System.setOut(stdout);
                PrintStream stderr = new PrintStream(outputDir + outputFilename
                        .replace(".out", ".stderr"));
                System.setErr(stderr);
                String[] params = in.nextLine().split(" ");
                Solution test = new Solution();
                int add = test.addition(
                        Integer.parseInt(params[0]),
                        Integer.parseInt(params[1])
                );
                writer.println(add);
                int sub = test.subtraction(
                        Integer.parseInt(params[0]),
                        Integer.parseInt(params[1])
                );
                writer.println(sub);
                int multi = test.multiplication(
                        Integer.parseInt(params[0]),
                        Integer.parseInt(params[1])
                );
                writer.println(multi);
                float divi = test.division(
                        Integer.parseInt(params[0]),
                        Integer.parseInt(params[1])
                );
                writer.println(divi);
                writer.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
