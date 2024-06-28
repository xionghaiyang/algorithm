package com.sean.base.chapter43;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-19 21:14
 * @Description: TODO
 */
public class Code02_TSP {

    public int t1(int[][] matrix) {
        int N = matrix.length;//0...N-1
        //set
        //set.get(i) != null i这座城市在集合里
        //set.get(i) == null i这座城市不在集合里
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            set.add(1);
        }
        return func1(matrix, set, 0);
    }

    //任何两座城市之间的距离，可以在matrix里面拿到
    //set中表示者哪些城市的集合
    //start这座成一定在set里
    //从start触发，要把set中所有的城市过一边，最终回到0这座城市，最小的距离是多少
    private int func1(int[][] matrix, List<Integer> set, int start) {
        int cityNum = 0;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                cityNum++;
            }
        }
        if (cityNum == 1) {
            return matrix[start][0];
        }
        //cityNum>1 不只start这一座城
        set.set(start, null);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                int cur = matrix[start][i] + func1(matrix, set, i);
                min = Math.min(min, cur);
            }
        }
        set.set(start, 1);
        return min;
    }

    public int t2(int[][] matrix) {
        int N = matrix.length;//0...N-1
        //7座城 1111111
        int allCity = (1 << N) - 1;
        return f2(matrix, allCity, 0);
    }

    private int f2(int[][] matrix, int cityStatus, int start) {
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }
        cityStatus &= (~(1 << start));
        int min = Integer.MAX_VALUE;
        //枚举所有的城市
        for (int move = 0; move < matrix.length; move++) {
            if ((cityStatus & (1 << move)) != 0) {
                int cur = matrix[start][move] + f2(matrix, cityStatus, move);
                min = Math.min(min, cur);
            }
        }
        cityStatus |= (1 << start);
        return min;
    }

    public int t3(int[][] matrix) {
        int N = matrix.length;//0...N-1
        //7座城 1111111
        int allCity = (1 << N) - 1;
        int[][] dp = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        return f3(matrix, allCity, 0, dp);
    }

    private int f3(int[][] martix, int cityStatus, int start, int[][] dp) {
        if (dp[cityStatus][start] != -1) {
            return dp[cityStatus][start];
        }
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            dp[cityStatus][start] = martix[start][0];
        } else {
            //把start位的1去掉
            cityStatus &= (~(1 << start));
            int min = Integer.MAX_VALUE;
            //枚举所有的城市
            for (int move = 0; move < martix.length; move++) {
                if (move != start && (cityStatus & (1 << move)) != 0) {
                    int cur = martix[start][move] + f3(martix, cityStatus, move, dp);
                    min = Math.min(min, cur);
                }
            }
            cityStatus |= (1 << start);
            dp[cityStatus][start] = min;
        }
        return dp[cityStatus][start];
    }

    public int t4(int[][] matrix) {
        int N = matrix.length;//0...N-1
        int statusNums = 1 << N;
        int[][] dp = new int[statusNums][N];
        for (int status = 0; status < statusNums; status++) {
            for (int start = 0; start < N; start++) {
                if ((status & (1 << start)) != 0) {
                    if (status == (status & (~status + 1))) {
                        dp[status][start] = matrix[start][0];
                    } else {
                        int min = Integer.MAX_VALUE;
                        //start城市在status里去掉之后的状态
                        int preStatus = status & (~(1 << start));
                        //start->i
                        for (int i = 0; i < N; i++) {
                            if ((preStatus & (1 << i)) != 0) {
                                int cur = matrix[start][i] + dp[preStatus][i];
                                min = Math.min(min, cur);
                            }
                        }
                        dp[status][start] = min;
                    }
                }
            }
        }
        return dp[statusNums - 1][0];
    }

    public int tsp1(int[][] matrix, int origin) {
        if (matrix == null || matrix.length < 2 || origin < 0 || origin >= matrix.length) {
            return 0;
        }
        //要考虑的集合
        ArrayList<Integer> cities = new ArrayList<>();
        //cities[0] != null 表示0城在集合里
        //cities[i] != null 表示i城在集合里
        for (int i = 0; i < matrix.length; i++) {
            cities.add(1);
        }
        //null,1,1,1,1,1,1
        //origin城不参与集合
        cities.set(origin, null);
        return process(matrix, origin, cities, origin);
    }

    //matrix所有距离，存在其中
    //origin固定参数，唯一的目标
    //cities要考虑的集合，一定不含有origin
    //当前来到的城市是谁,cur
    private int process(int[][] matrix, int aim, ArrayList<Integer> cities, int cur) {
        boolean hasCity = false;//集团中是否还有城市
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i) != null) {
                hasCity = true;
                cities.set(i, null);
                //martrix[cur][i] + f(i,集团(去掉i))
                ans = Math.min(ans, matrix[cur][i] + process(matrix, aim, cities, i));
                cities.set(i, 1);
            }
        }
        return hasCity ? ans : matrix[cur][aim];
    }

    public int tsp2(int[][] matrix, int origin) {
        if (matrix == null || matrix.length < 2 || origin < 0 || origin > matrix.length) {
            return 0;
        }
        int N = matrix.length - 1;//除去origin之后是N-1个点
        int S = 1 << N;//状态数量
        int[][] dp = new int[S][N];
        int icity = 0;
        int kcity = 0;
        for (int i = 0; i < N; i++) {
            icity = i < origin ? i : i + 1;
            dp[0][i] = matrix[icity][origin];
        }
        for (int status = 1; status < S; status++) {
            //尝试每一种状态status = 0 0 1 0 0 0 0 0 0
            //下标8 7 6 5 4 3 2 1 0
            for (int i = 0; i < N; i++) {
                //i枚举的出发城市
                dp[status][i] = Integer.MAX_VALUE;
                if ((1 << i & status) != 0) {
                    //如果i这座城是可以枚举的,i=6,i对应的原始城的编号，icity
                    icity = i < origin ? i : i + 1;
                    for (int k = 0; k < N; k++) {
                        if ((1 << k & status) != 0) {//i这一步连到的点k
                            kcity = k < origin ? k : k + 1;//k对应原始城的编号,kcity
                            dp[status][i] = Math.min(dp[status][i], dp[status ^ (1 << i)][k] + matrix[icity][kcity]);
                        }
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            icity = i < origin ? i : i + 1;
            res = Math.min(res, dp[S - 1][i] + matrix[origin][icity]);
        }
        return res;
    }

    public int[][] generateGraph(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public static void main(String[] args) {
        Code02_TSP solution = new Code02_TSP();
        int len = 10;
        int value = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < 200; i++) {
            int[][] matrix = solution.generateGraph(len, value);
            int origin = (int) (Math.random() * matrix.length);
            int res1 = solution.t1(matrix);
            int res2 = solution.t2(matrix);
            int res3 = solution.t3(matrix);
            int res4 = solution.t4(matrix);
            int res5 = solution.tsp1(matrix, origin);
            int res6 = solution.tsp2(matrix, origin);
            if (res1 != res2 || res1 != res3 || res1 != res4 || res1 != res5 || res1 != res6) {
                System.out.println("Oops!");
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(res3);
                System.out.println(res4);
                System.out.println(res5);
                System.out.println(res6);
                break;
            }
        }
        System.out.println("功能测试结束");

        len = 22;
        System.out.println("性能测试开始,数据规模:" + len);
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * value) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        long start;
        long end;
        start = System.currentTimeMillis();
        solution.t4(matrix);
        end = System.currentTimeMillis();
        System.out.println("运行时间:" + (end - start) + " 毫秒");
        System.out.println("性能测试结束");
    }

}
