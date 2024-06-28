package com.sean.interview.adecco.question3;

import java.util.Scanner;

public class Main {

    private static int N;
    private static int[][] dp;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < M; i++) {
            String[] split = sc.nextLine().split(" ");
            int u = Integer.parseInt(split[0]);
            int v = Integer.parseInt(split[1]);
            int w = Integer.parseInt(split[2]);
            dp[u][v] = w;
        }
        String[] split = sc.nextLine().split(" ");
        int u = Integer.parseInt(split[0]);
        int v = Integer.parseInt(split[1]);
        visited = new boolean[N + 1][N + 1];
        process(u, v);
        System.out.println(dp[u][v]);
    }

    private static void process(int u, int v) {
        if (visited[u][v]) {
            return;
        }
        for (int k = 1; k <= N; k++) {
            if (k != u && k != v) {
                process(u, k);
                if (dp[u][k] == -1) {
                    continue;
                }
                process(k, v);
                if (dp[k][v] == -1) {
                    continue;
                }
                if (dp[u][v] == -1) {
                    dp[u][v] = dp[u][k] + dp[k][v];
                } else {
                    dp[u][v] = Math.min(dp[u][v], dp[u][k] + dp[k][v]);
                }
            }
        }
        visited[u][v] = true;
    }

}
