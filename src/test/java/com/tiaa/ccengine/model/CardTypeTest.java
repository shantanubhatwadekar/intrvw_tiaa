package com.tiaa.ccengine.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTypeTest {

    @Test
    public void getType() {
        Assert.assertNull(CardType.getType("abcd"));
        Assert.assertEquals(CardType.getType("amex"), CardType.AMEX);
        Assert.assertEquals(CardType.getType("AmEx"), CardType.AMEX);

        Assert.assertEquals(CardType.getType("master"), CardType.MASTER);
        Assert.assertEquals(CardType.getType("MaStEr"), CardType.MASTER);

        Assert.assertEquals(CardType.getType("visa"), CardType.VISA);
        Assert.assertEquals(CardType.getType("vIsA"), CardType.VISA);

        Assert.assertEquals(CardType.getType("discover"), CardType.DISCOVER);
        Assert.assertEquals(CardType.getType("diScoVer"), CardType.DISCOVER);
    }
}