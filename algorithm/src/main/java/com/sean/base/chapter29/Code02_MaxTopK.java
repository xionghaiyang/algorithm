package com.sean.base.chapter29;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-29 20:30
 * @Description: TODO
 */
public class Code02_MaxTopK {

    //时间复杂度O(N*logN)
    //排序+收集
    public int[] maxTopK1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        k = Math.min(N, k);
        Arrays.sort(arr);
        int[] res = new int[k];
        for (int i = N - 1, j = 0; j < k; i--, j++) {
            res[j] = arr[i];
        }
        return res;
    }

    //方法二，时间复杂度O(N+K*logN)
    //解释：堆
    public int[] maxTopK2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        k = Math.min(N, k);
        //从底向上建堆，时间复杂度O(N)
        for (int i = N - 1; i >= 0; i--) {
            heapify(arr, i, N);
        }
        //只把前K个数放在arr末尾，然后收集，O(K*logN)
        int heapSize = N;
        swap(arr, 0, --heapSize);
        int count = 1;
        while (heapSize > 0 && count < k) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
            count++;
        }
        int[] res = new int[k];
        for (int i = N - 1, j = 0; j < k; i--, j++) {
            res[j] = arr[i];
        }
        return res;
    }

    private void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //方法三，时间复杂度O(N+k*logk)
    public int[] maxTopK3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        k = Math.min(N, k);
        //O(N)
        int num = minKth(arr, N - k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > num) {
                res[index++] = arr[i];
            }
        }
        for (; index < k; index++) {
            res[index] = num;
        }
        //O(K*logk)
        Arrays.sort(res);
        for (int L = 0, R = k - 1; L < R; L++, R--) {
            swap(res, L, R);
        }
        return res;
    }

    //时间复杂度O(N)
    private int minKth(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    private int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code02_MaxTopK solution = new Code02_MaxTopK();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean pass = true;
        System.out.println("测试开始，没有打印出错信息说明测试通过");
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = solution.generateRandomArray(maxSize, maxValue);
            int[] arr1 = solution.copyArray(arr);
            int[] arr2 = solution.copyArray(arr);
            int[] arr3 = solution.copyArray(arr);
            int[] res1 = solution.maxTopK1(arr1, k);
            int[] res2 = solution.maxTopK2(arr2, k);
            int[] res3 = solution.maxTopK3(arr3, k);
            if (!solution.isEqual(res1, res2) || !solution.isEqual(res1, res3)) {
                pass = false;
                System.out.println("出错了");
                solution.printArray(res1);
                solution.printArray(res2);
                solution.printArray(res3);
                break;
            }
        }
        System.out.println("测试结束了，测试了" + testTime + "组，是否所有测试用例都通过？" + (pass ? "是" : "否"));
    }

}
