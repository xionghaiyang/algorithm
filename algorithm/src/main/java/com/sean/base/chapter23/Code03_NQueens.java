package com.sean.base.chapter23;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-15 21:01
 * @Description: TODO
 */
public class Code03_NQueens {

    public int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    //当前来到i行，一共是0~N-1行
    //在i行上放皇后，所有列都尝试
    //必须要保证跟之前所有的桓侯不打架
    //int[] record record[x]=y之前的第x行的皇后，放在了y列
    //返回：不关心i以上发生了什么，i...后续有多少合法的方法数
    private int process1(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        //i行的皇后，放哪一列呢？j列
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    private boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    //请不要超过32皇后问题
    public int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        //如果你是13皇后问题,limit最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    //7皇后问题
    //limit : 0...0 1 1 1 1 1 1 1
    //之前皇后的列影响:colLim
    //之前皇后的左下对角线影响:leftDiaLim
    //之前皇后的右下对角线影响:rightDiaLim
    private int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        //pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Code03_NQueens solution = new Code03_NQueens();
        int n = 15;
        long start = System.currentTimeMillis();
        System.out.println(solution.num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(solution.num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time:" + (end - start) + "ms");
    }

}
