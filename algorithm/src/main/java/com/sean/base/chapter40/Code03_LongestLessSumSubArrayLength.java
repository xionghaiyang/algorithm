package com.sean.base.chapter40;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-20 20:18
 * @Description: TODO
 */
public class Code03_LongestLessSumSubArrayLength {

    public int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] midSums = new int[n];
        int[] minSumEnds = new int[n];
        midSums[n - 1] = arr[n - 1];
        minSumEnds[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (midSums[i + 1] < 0) {
                midSums[i] = arr[i] + midSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                midSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        //迟迟扩不进来那块的开头位置
        int end = 0;
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            //while循环结束之后
            //1)如果以i开头的情况下，累加和<=k的最长子数组是arr[i...end-1],看看这个子数组长度能不能更新res
            //2)如果以i开头的情况下，累加和<=k的最长子数组比arr[i...end-1]短,更新还是不更新res都不会影响最终结果
            while (end < n && sum + midSums[end] <= k) {
                sum += midSums[end];
                end = minSumEnds[end] + 1;
            }
            ans = Math.max(ans, end - i);
            if (end > i) {//还有窗口，哪怕窗口没有数字[i~end][4,4)
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return ans;
    }

    public int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    private int getLessIndex(int[] arr, int num) {
        int low = 0;
        int hight = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= hight) {
            mid = low + ((hight - low) >> 1);
            if (arr[mid] >= num) {
                res = mid;
                hight = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    public int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        Code03_LongestLessSumSubArrayLength solution = new Code03_LongestLessSumSubArrayLength();
        System.out.println("test begin");
        for (int i = 0; i < 1000000; i++) {
            int[] arr = solution.generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (solution.maxLengthAwesome(arr, k) != solution.maxLength(arr, k)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
