package com.tiaa.ccengine.controller;

import com.tiaa.ccengine.factory.CCEngineFactory;
import com.tiaa.ccengine.model.CardType;
import com.tiaa.ccengine.model.CreditCard;
import com.tiaa.ccengine.service.CCEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebInputException;

import java.util.Collection;

@RestController
@RequestMapping("/CCEngine/{cardtype}/{count}")
public class CCEngineController {
    private static final Logger logger = LoggerFactory.getLogger(CCEngineController.class);
    private static final String REQUEST_COUNT_EXCEEDS = "CC Generator can generate at most 100000 numbers per request.";

    CCEngine ccEngine;

    @GetMapping
    Collection<CreditCard> generateCreditCardNumbers(@PathVariable String cardtype, @PathVariable int count) throws InstantiationException {
        logger.info("Request received : cardtype : {} , count : {}", cardtype, count);
        CardType type = CardType.getType(cardtype);

        if(count > 100000) {
            logger.error(REQUEST_COUNT_EXCEEDS);
            throw new ServerWebInputException(REQUEST_COUNT_EXCEEDS);
        }
        if (type == null) {
            String reason = cardtype + " is not a valid type of card.";
            logger.error(reason);
            throw new ServerWebInputException(reason);
        }
        ccEngine = CCEngineFactory.INSTANCE.getCCEngine(type);
        if (ccEngine == null) {
            String reason = "Some error occurred while initializing Credit Card Engine instance for card type : " + type;
            logger.error(reason);
            throw new InstantiationException(reason);
        }
        return ccEngine.generateCreditCardNumbers(count);
    }
}