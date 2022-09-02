package com.tolulope.seamfixtest.algorithm;

import java.util.Arrays;

public class Question3 {

    public static int fraudWarning(int n, int d, int[] numbers) {
        int count = 0;

        String medianType = d % 2 == 0 ? "even" : "odd";
        for (int i = d; i < n; i++) {
            int[] subsetOfNumbers = Arrays.copyOfRange(numbers, i-d, i);
            Arrays.sort(subsetOfNumbers);
            float median;
            if(medianType.equals("odd")) {
                median = subsetOfNumbers[d/2];
            }else{
                median = (float)(subsetOfNumbers[d/2] + subsetOfNumbers[d/2-1])/2;
            }
            if(numbers[i] >= (2 * median)) {
                count++;
            }
        }
        return count;
    }
}
