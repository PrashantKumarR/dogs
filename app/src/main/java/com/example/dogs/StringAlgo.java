package com.example.dogs;

import java.util.Arrays;

public class StringAlgo {
    public static int getVowelCount(String input) {
        if (input == null) {
            return 0;
        }
        int count = 0;
        String vowel = "aeiouAEIOU";
        for (int i = 0; i < input.length(); i++) {
            if (vowel.contains(input.charAt(i) + "")) {
                count++;
            }
        }
        return count;
    }

    public static int getConsonantCount(String input) {
        if (input == null) {
            return 0;
        }
        int count = 0;
        String consonant = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        for (int i = 0; i < input.length(); i++) {
            if (consonant.contains(input.charAt(i) + "")) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static void minimumBribes(int[] q) {
        int ans = 0;
        for (int i = q.length - 1; i >= 0; i--) {
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            for (int j = Math.max(0, q[i] - 2); j < i; j++) {
                if (q[j] > q[i]) {
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }





    static String timeConversion(String s) {
        if (s.endsWith("AM")) {
            String[] splitTime = s.replace("AM", "").split(":");
            int hh = Integer.parseInt(splitTime[0]);
            hh = hh == 12 ? 0 : hh;
            return String.format("%02d:%s:%s", hh, splitTime[1], splitTime[2]);
        }
        String[] splitTime = s.replace("PM", "").split(":");
        int hh = Integer.parseInt(splitTime[0]);
        hh = (hh + 12) % 24;
        hh = hh == 0 ? 12 : hh;
        return String.format("%02d:%s:%s", hh, splitTime[1], splitTime[2]);
    }

    static int birthdayCakeCandles(int[] ar) {
        int count = 1;
        int max = Integer.MIN_VALUE;

        for (int val : ar) {
            if (max > val) {
                continue;
            } else if (max == val) {
                count++;
            } else {
                max = val;
                count = 1;
            }
        }
        return count;
    }

    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long sum = 0;
        for (int index = 0; index < arr.length; index++) {
            sum += arr[index];
            if (min > arr[index])
                min = arr[index];
            if (max < arr[index])
                max = arr[index];

//            if(index == 0){
//                min+=arr[index];
//            } else if (index == arr.length-1){
//                max+=arr[index];
//            }else {
//                min+=arr[index];
//                max+=arr[index];
//            }
        }
        System.out.println(String.format("%d %d", sum - max, sum - min));
    }

    static void plusMinus(int[] arr) {
        float plus, minus, zero;
        plus = minus = zero = 0;
        for (int val : arr) {
            if (val < 0)
                minus++;
            else if (val > 0)
                plus++;
            else zero++;
        }

        System.out.println(plus / arr.length);
        System.out.println(minus / arr.length);
        System.out.println(zero / arr.length);
    }

    static void staircase(int n) {
        int blank = n - 1;
        while (blank >= 0) {
            for (int index = 0; index < n; index++) {
                if (index < blank) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
            }
            blank--;
            System.out.println();
        }

    }

    static void permutation(String input) {
        permutation("", input);
    }

    static void permutation(String fixed, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(fixed);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permutation(fixed + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1, remaining.length()));

            }
        }
    }

    static void reverseWordOrder(String paragraph){
        String[] words = paragraph.split("\\s");
//        for (int i =0; i< )
    }

    void printPattern(){
        for (int i = 0; i < 5; i++) {
            int x = 6 - i - 1;
            System.out.print(String.format("%" + x + "s", " "));
            while (x<6) {
                System.out.print("a");
                x++;
            }

            System.out.println();
        }
    }
}
