package com.tiaa.ccengine.util;

import org.junit.Assert;
import org.junit.Test;

public class CCEngineUtilTest {

    @Test
    public void isValidCCNumber() {
        Assert.assertTrue(CCEngineUtil.isValidCCNumber(new int[]{5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0}));
        Assert.assertTrue(CCEngineUtil.isValidCCNumber(new int[]{4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 2}));
        Assert.assertTrue(CCEngineUtil.isValidCCNumber(new int[]{6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 8}));
        Assert.assertTrue(CCEngineUtil.isValidCCNumber(new int[]{3, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 9, 7}));

        Assert.assertFalse(CCEngineUtil.isValidCCNumber(new int[]{5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 9}));
        Assert.assertFalse(CCEngineUtil.isValidCCNumber(new int[]{4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1}));
        Assert.assertFalse(CCEngineUtil.isValidCCNumber(new int[]{6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 7}));
        Assert.assertFalse(CCEngineUtil.isValidCCNumber(new int[]{3, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 9, 6}));
    }

    @Test
    public void getDigitArrayFromLog() {
        int[] result = CCEngineUtil.getDigitsFromLong(1234);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4}, result);

        result = CCEngineUtil.getDigitsFromLong(1234567887654321L);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1}, result);
    }
}