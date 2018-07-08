package com.tiaa.ccengine.service;

import com.tiaa.ccengine.dao.CCEngineDao;
import com.tiaa.ccengine.model.CardType;
import com.tiaa.ccengine.model.CreditCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.tiaa.ccengine.util.CCEngineUtil.getDigitsFromLong;
import static com.tiaa.ccengine.util.CCEngineUtil.isValidCCNumber;

public abstract class CCEngine {
    static long getLastGeneratedCCNumber(final CardType cardType) {
        return CCEngineDao.getLastGeneratedCCNumber(cardType);
    }

    protected abstract AtomicLong getlastValidCCNumber();

    protected abstract CardType getCardType();

    protected abstract long getUpperBound();


    public Collection<CreditCard> generateCreditCardNumbers(int count) {
        List<CreditCard> cardList = new ArrayList<>();
        long newCCnumber = getlastValidCCNumber().incrementAndGet();
        for (int i = 0; i < count && newCCnumber <= getUpperBound(); ++newCCnumber) {
            if (isValidCCNumber(getDigitsFromLong(newCCnumber))) {
                boolean success = CCEngineDao.setCCNumber(newCCnumber, getCardType());
                if (success) {
                    cardList.add(new CreditCard(newCCnumber, LocalDate.now()));
                    getlastValidCCNumber().set(newCCnumber);
                    ++i;
                } else {
                    newCCnumber = getlastValidCCNumber().incrementAndGet();
                }
            }
        }
        return cardList;
    }
}