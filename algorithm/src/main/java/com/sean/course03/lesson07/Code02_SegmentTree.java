package com.sean.course03.lesson07;

/**
 * @Author xionghaiyang
 * @Date 2025-04-11 11:25
 * @Description 线段树
 */
public class Code02_SegmentTree {

    public static class SegmentTree {

        private int maxN;
        //arr[]为原序列信息从0位置开始，但在arr里是从1位置开始
        private int[] arr;
        //sum[]模拟线段树维护区间和
        private long[] sum;
        //lazy[]为累加懒惰标记
        private int[] lazy;
        //change[]为更新的值
        private int[] change;
        //update[]为更新懒惰标记
        private boolean[] update;

        public SegmentTree(int[] origin) {
            maxN = origin.length + 1;
            //arr[0]不用，从1开始使用
            arr = new int[maxN];
            for (int i = 1; i < maxN; i++) {
                arr[i] = origin[i - 1];
            }
            //某一范围德累加和信息
            sum = new long[maxN << 2];
            //某一个范围没有往下传递的累加任务
            lazy = new int[maxN << 2];
            //某一个范围更新任务，更新成了什么
            change = new int[maxN << 2];
            //某一个范围有没有更新操作
            update = new boolean[maxN << 2];
        }

        //在初始化阶段，先把sum数组填好
        //在arr[l...r]范围上
        //index:这个范围在sum中的下标
        public void build(int l, int r, int index) {
            if (l == r) {
                sum[index] = arr[l];
                return;
            }
            int mid = l + ((r - l) >> 1);
            build(l, mid, index << 1);
            build(mid + 1, r, index << 1 | 1);
            pushUp(index);
        }

        private void pushUp(int index) {
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }

        //L..R->任务范围，所有的值累加上C
        //l..r ->本次需要调整的范围
        //index ->去哪找l..r范围上的信息
        public void add(int L, int R, int C, int l, int r, int index) {
            //任务的范围彻底覆盖了，本次需要调整的范围
            if (L <= l && r <= R) {
                sum[index] += C * (r - l + 1);
                lazy[index] += C;
                return;
            }
            //任务L..R并没有把l..r全包住
            //要把当前任务往下发
            int mid = l + ((r - l) >> 1);
            //下发之前攒的所有懒任务
            pushDown(index, mid - l + 1, r - mid);
            //左孩子是否需要接到任务
            if (L <= mid) {
                add(L, R, C, l, mid, index << 1);
            }
            //右孩子是否需要接到任务
            if (R > mid) {
                add(L, R, C, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }

        //将之前所有的懒增加和懒更新，从父范围，发给左右两个子范围
        //lSize表示左子树元素节点个数，rSize表示右子树元素节点个数
        private void pushDown(int index, int lSize, int rSize) {
            if (update[index]) {
                update[index << 1] = true;
                update[index << 1 | 1] = true;
                change[index << 1] = change[index];
                change[index << 1 | 1] = change[index];
                lazy[index << 1] = 0;
                lazy[index << 1 | 1] = 0;
                sum[index << 1] = change[index] * lSize;
                sum[index << 1 | 1] = change[index] * rSize;
                update[index] = false;
            }
            if (lazy[index] != 0) {
                lazy[index << 1] += lazy[index];
                sum[index << 1] += lazy[index] * lSize;
                lazy[index << 1 | 1] += lazy[index];
                sum[index << 1 | 1] += lazy[index] * rSize;
                lazy[index] = 0;
            }
        }

        //L..R->任务范围，所有的值更新成C
        //l..r ->本次需要调整的范围
        //index ->去哪找l..r范围上的信息
        public void update(int L, int R, int C, int l, int r, int index) {
            //任务的范围彻底覆盖了，本次需要调整的范围
            if (L <= l && r <= R) {
                update[index] = true;
                change[index] = C;
                sum[index] = C * (r - l + 1);
                lazy[index] = 0;
                return;
            }
            //任务L..R并没有把l..r全包住
            //要把当前任务往下发
            int mid = l + ((r - l) >> 1);
            pushDown(index, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, index << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }

        public long query(int L, int R, int l, int r, int index) {
            if (L <= l && r <= R) {
                return sum[index];
            }
            int mid = l + ((r - l) >> 1);
            pushDown(index, mid - l + 1, r - mid);
            long res = 0;
            if (L <= mid) {
                res += query(L, R, l, mid, index << 1);
            }
            if (R > mid) {
                res += query(L, R, mid + 1, r, index << 1 | 1);
            }
            return res;
        }
    }

    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long res = 0;
            for (int i = L; i <= R; i++) {
                res += arr[i];
            }
            return res;
        }
    }

    private static int[] genarateRandomArray(int len, int max) {
        int size = (int) (len * Math.random()) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (max * Math.random()) - (int) (max * Math.random());
        }
        return origin;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right right = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (N * Math.random()) + 1;
                int num2 = (int) (N * Math.random()) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (max * Math.random()) - (int) (max * Math.random());
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    right.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    right.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (N * Math.random()) + 1;
                int num2 = (int) (N * Math.random()) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long res1 = seg.query(L, R, S, N, root);
                long res2 = right.query(L, R);
                if (res1 != res2) {
                    System.out.println("出错了");
                    break;
                }
            }
        }
        System.out.println("测试结束");
    }

}
