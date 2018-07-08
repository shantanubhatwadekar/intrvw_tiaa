package com.tiaa.ccengine.factory;

import com.tiaa.ccengine.model.CardType;
import com.tiaa.ccengine.service.AmexCCEngine;
import com.tiaa.ccengine.service.DiscoverCCEngine;
import com.tiaa.ccengine.service.MasterCCEngine;
import com.tiaa.ccengine.service.VisaCCEngine;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CCEngineFactoryTest {

    @Test
    public void getCCEngine() {
        Assert.assertTrue(CCEngineFactory.INSTANCE.getCCEngine(CardType.AMEX) instanceof AmexCCEngine);
        Assert.assertTrue(CCEngineFactory.INSTANCE.getCCEngine(CardType.MASTER) instanceof MasterCCEngine);
        Assert.assertTrue(CCEngineFactory.INSTANCE.getCCEngine(CardType.VISA) instanceof VisaCCEngine);
        Assert.assertTrue(CCEngineFactory.INSTANCE.getCCEngine(CardType.DISCOVER) instanceof DiscoverCCEngine);
    }
}