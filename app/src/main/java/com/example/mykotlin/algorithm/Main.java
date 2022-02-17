package com.example.mykotlin.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wh on 2021/8/26
 * 算法
 */
class Main {
    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 5, 3, 9, 7};
        System.out.println(Arrays.toString(twoSum(nums, 9)));

        System.out.println(reverse(-14564));
        System.out.println(isPalindrome(14541));

        int[] nums2 = {1, 2, 2, 3, 3, 4, 5};
        System.out.println(removeDuplicates(nums2));

        int[] nums3 = {1, 2, 2, 3, 3, 4, 5};
        System.out.println(removeElement(nums3, 3));

        int[] merge1 = {1, 2, 3, 0, 0, 0, 0};
        int[] merge2 = {2, 4, 5, 6};
        merge(merge1, 3, merge2, 4);

        System.out.println(generate(5));

        int[] single = {2, 4, 2, 5, 6, 5, 6};
        System.out.println(singleNumber(single));
    }


    /**
     * 两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];

        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                index[0] = i;
                index[1] = hash.get(nums[i]);
                return index;
            }
            hash.put(target - nums[i], i);
        }

//        for (int i = 0; i < nums.length; i++) {
//            for (int j = nums.length - 1; j > i; j--) {
//                if (nums[i] + nums[j] == target) {
//                    index[0] = i;
//                    index[1] = j;
//                    return index;
//                }
//            }
//        }
        return index;
    }

    /**
     * 数字反转
     */
    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            // 每次取末尾数
            int tmp = x % 10;
            // 判断是否大于最大整数
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && tmp > 7)) {
                return 0;
            }
            // 判断是否小于最小整数
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            //每次循环x都会除以10，所以res每次乘以10
            x /= 10;
        }

        return res;
    }

    /**
     * 判断回文数
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int newNum = 0;
        while (x > newNum) {
            newNum = newNum * 10 + x % 10;
            x /= 10;
        }
        return newNum == x || x == (newNum / 10);

//        String old = "" + x;
//        String newStr = new StringBuilder("" + x).reverse().toString();
//        return old.equals(newStr);

    }

    /**
     * 删除有序数组中的重复项
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return j + 1;

    }

    /**
     * 移除元素
     */
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return j;

    }

    /**
     * 合并两个有序数组
     *
     * @param nums1 合并后数组，长度为合并后长度
     * @param m     num1中数组真正长度，尾部补0
     * @param nums2 数组2
     * @param n     数组2长度
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // num1长度 = 合并后总长度
        int total = nums1.length - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        while (p2 >= 0) {
            // 从尾部填充，大的数放后面
            // p1小于0是防止num1已填充完毕后 num2还没填充完
            if (p1 < 0 || nums1[p1] <= nums2[p2]) {
                nums1[total--] = nums2[p2--];
            } else {
                nums1[total--] = nums1[p1--];
            }
        }
        System.out.println(Arrays.toString(nums1));

    }


    /**
     * 杨辉三角
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    // 每一行第一位和最后一位为1
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;

    }

    /**
     * 只出现一次的数字
     */
    public static int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single = single ^ num;
        }
        return single;

    }

}
