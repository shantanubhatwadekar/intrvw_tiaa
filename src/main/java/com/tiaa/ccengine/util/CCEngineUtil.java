package com.tiaa.ccengine.util;

import java.util.LinkedList;
import java.util.List;

public class CCEngineUtil {
    private CCEngineUtil() {
    }

    public static boolean isValidCCNumber(int[] digits) {
        int sum = 0;
        int length = digits.length;
        for (int i = 0; i < length; i++) {

            // get digits in reverse order
            int digit = digits[length - i - 1];

            // every 2nd number multiply with 2
            if (i % 2 == 1) {
                digit *= 2;
            }
            sum += digit > 9 ? digit - 9 : digit;
        }
        return sum % 10 == 0;
    }

    public static int[] getDigitsFromLong(long number) {
        List<Integer> list = new LinkedList<>();

        while(number > 0) {
            list.add(0, (int)(number%10));
            number = number / 10;
        }
        int[] digits = new int[list.size()];
        for(int i = 0;i < digits.length; ++i) {
            digits[i] = list.get(i);
        }
        return digits;
    }
}
