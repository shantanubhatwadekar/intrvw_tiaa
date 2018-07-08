package com.tiaa.ccengine.service;

import com.tiaa.ccengine.model.CardType;

import java.util.concurrent.atomic.AtomicLong;

public class MasterCCEngine extends CCEngine {
    private static AtomicLong lastGeneratedCCNumber;

    private static final long LOWER_BOUND = 5000_0000_0000_0000L;

    private static final long UPPER_BOUND = 5999_9999_9999_9999L;

    static {
        synchronized (CardType.MASTER) {
            long dbNumber = getLastGeneratedCCNumber(CardType.MASTER);
            lastGeneratedCCNumber = new AtomicLong(dbNumber != -1 ? dbNumber : LOWER_BOUND);
        }
    }

    protected AtomicLong getlastValidCCNumber() {
        return lastGeneratedCCNumber;
    }

    protected CardType getCardType() {
        return CardType.MASTER;
    }

    protected long getUpperBound() {
        return UPPER_BOUND;
    }
}
