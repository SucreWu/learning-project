package com.wujie.learning.leetcode;

import java.util.HashMap;

/**
 * 两数之和
 *
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 */
public class leetcode1 {
    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            for (int j = i+1; j < nums.length; j++){
                if (nums[i] == target - nums[j]){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            if (map.containsKey(temp)){
                return new int[]{i ,map.get(temp)};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        nums = twoSum2(nums,5);
        System.out.println(nums[0] + "，" + nums[1]);
    }
}
