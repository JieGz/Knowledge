package com.demo.algorithms;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jieguangzhi
 * @date 2024-07-30
 */
public class Solution {

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        //System.out.println(Arrays.toString(Arrays.stream(solution.twoSum(new int[]{2, 7, 11, 15}, 9)).toArray()));
        solution.isPalindrome2(100011);
    }

    public int[] twoSum(int[] nums, int target) throws Exception {
        //key: num, value: index
        Map<Integer, Integer> numMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (numMap.containsKey(value)) {
                return new int[]{numMap.get(value), i};
            }
            numMap.put(nums[i], i);
        }
        return new int[0];
    }

    public boolean isPalindrome(int x) {
        final String value = String.valueOf(x);
        final byte[] valueByte = value.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < valueByte.length / 2; i++) {
            if (valueByte[i] != valueByte[valueByte.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome1(int x) {
        String value = String.valueOf(x);
        int length = value.length();
        for (int i = 0; i < length / 2; i++) {
            if (value.charAt(length - i - 1) != value.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int div = 10;
        //拿到最大的倍数
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;//对dlv求余去掉左边的数据以及左边为0的地方，除10最掉个位数部份
            div /= 100;
        }
        return true;
    }
}
