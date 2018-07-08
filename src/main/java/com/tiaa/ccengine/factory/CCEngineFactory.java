package com.tiaa.ccengine.factory;

import com.tiaa.ccengine.model.CardType;
import com.tiaa.ccengine.service.*;

public enum CCEngineFactory {
    INSTANCE;

    public CCEngine getCCEngine(CardType cardType) {
        switch (cardType) {
            case AMEX:
                return new AmexCCEngine();
            case VISA:
                return new VisaCCEngine();
            case MASTER:
                return new MasterCCEngine();
            case DISCOVER:
                return new DiscoverCCEngine();
            default:
                return null;
        }
    }
}