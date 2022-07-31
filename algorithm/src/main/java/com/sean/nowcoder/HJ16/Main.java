package com.sean.nowcoder.HJ16;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            int m = sc.nextInt();
            Good[] goods = new Good[m];
            for (int i = 0; i < m; i++) {
                goods[i] = new Good();
            }
            for (int i = 0; i < m; i++) {
                int v = sc.nextInt();
                int p = sc.nextInt();
                int q = sc.nextInt();
                goods[i].setVpq(v, p, q);
                //如果不是主件
                if (q > 0) {
                    //找到其对应的主件编号，构建主件和附件的关系
                    if (goods[q - 1].getA1() == -1) {
                        goods[q - 1].setA1(i);
                    } else {
                        goods[q - 1].setA2(i);
                    }
                }
            }
            pack(N, m, goods);
        }

    }

    private static void pack(int N, int m, Good[] goods) {
        //dp[i]表示i元买到的最大价值
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 0; i < m; i++) {
            Good good = goods[i];
            //费用
            ArrayList<Integer> money = new ArrayList<>();
            //价值
            ArrayList<Integer> value = new ArrayList<>();
            //只处理主件的情况
            if (good.getQ() == 0) {
                //只放主件
                money.add(good.getV());
                value.add(good.getV() * good.getP());
                //放主件+附件1
                if (good.getA1() != -1) {
                    money.add(money.get(0) + goods[good.getA1()].getV());
                    value.add(value.get(0) + goods[good.getA1()].getV() * goods[good.getA1()].getP());
                }
                //放主件+附件2
                if (good.getA2() != -1) {
                    money.add(money.get(0) + goods[good.getA2()].getV());
                    value.add(value.get(0) + goods[good.getA2()].getV() * goods[good.getA2()].getP());
                }
                //放主件+附件1+附件2
                if (good.getA1() != -1 && good.getA2() != -1) {
                    money.add(money.get(0) + goods[good.getA1()].getV() + goods[good.getA2()].getV());
                    value.add(value.get(0) + goods[good.getA1()].getV() * goods[good.getA1()].getP() + goods[good.getA2()].getV() * goods[good.getA2()].getP());
                }
            }
            //满足不超N元的情况下的最大价值
            for (int k = N; k >= 0; k -= 10) {
                //遍历放主件i的四种情况
                for (int j = 0; j < money.size(); j++) {
                    //用最少的钱(第k元),就能买到的最大价值
                    if (k - money.get(j) >= 0) {
                        dp[k] = Math.max(dp[k], dp[k - money.get(j)] + value.get(j));
                    }
                }
            }
        }
        System.out.println(dp[N]);
    }

}

class Good {
    private int v; //价格
    private int p; //重要度
    private int q; //主附件id

    private int a1 = -1;//附件1
    private int a2 = -1;//附件2

    public void setVpq(int v, int p, int q) {
        this.v = v;
        this.p = p;
        this.q = q;
    }

    public int getV() {
        return v;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }
}

