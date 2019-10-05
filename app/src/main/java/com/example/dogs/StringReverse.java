package com.example.dogs;

public class StringReverse {

    public static String reverse(String input){
//        return reverseUsinCharArray(input);
        return reverseUsingStringBuilder(input);
//        return recursiveReverse(input);
    }
    private static String reverseUsinCharArray(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        char[] inputArray = input.toCharArray();
        int begin = 0;
        int end = input.length() - 1;

        while (begin < end) {
            char temp = inputArray[begin];
            inputArray[begin] = inputArray[end];
            inputArray[end] = temp;
            begin++;
            end--;
        }
        return new String(inputArray);
    }

    private static String reverseUsingStringBuilder(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder stringBuilder = new StringBuilder(input);
        stringBuilder.reverse();

        return new String(stringBuilder);
    }

    private static String recursiveReverse(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        return recursiveReverse(input.substring(1)) + input.charAt(0);
    }

}
