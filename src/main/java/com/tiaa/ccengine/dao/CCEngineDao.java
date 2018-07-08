package com.tiaa.ccengine.dao;

import com.tiaa.ccengine.model.CardType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CCEngineDao {
    private static final Logger logger = LoggerFactory.getLogger(CCEngineDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    static JdbcTemplate staticJdbcTemplate;

    @PostConstruct
    private void init() {
        staticJdbcTemplate = jdbcTemplate;
    }

    private static final String SELECT_MAX_AMEX_CC_NUMBER = "SELECT IFNULL(MAX(cc_number),-1) FROM AMEXNUMBERS";
    private static final String SELECT_MAX_MASTER_CC_NUMBER = "SELECT IFNULL(MAX(cc_number),-1) FROM MASTERNUMBERS";
    private static final String SELECT_MAX_VISA_CC_NUMBER = "SELECT IFNULL(MAX(cc_number),-1) FROM VISANUMBERS";
    private static final String SELECT_MAX_DISCOVER_CC_NUMBER = "SELECT IFNULL(MAX(cc_number),-1) FROM DISCOVERNUMBERS";

    private static final String INSERT_AMEX_CC_NUMBER = "INSERT INTO AMEXNUMBERS values(?)";
    private static final String INSERT_MASTER_CC_NUMBER = "INSERT INTO MASTERNUMBERS values(?)";
    private static final String INSERT_VISA_CC_NUMBER = "INSERT INTO VISANUMBERS values(?)";
    private static final String INSERT_DISCOVER_CC_NUMBER = "INSERT INTO DISCOVERNUMBERS values(?)";

    public static long getLastGeneratedCCNumber(CardType cardType) {
        long ccNumber = -1;
        try {
            switch (cardType) {
                case AMEX:
                    ccNumber = staticJdbcTemplate.queryForObject(SELECT_MAX_AMEX_CC_NUMBER, Long.class);
                    break;
                case VISA:
                    ccNumber = staticJdbcTemplate.queryForObject(SELECT_MAX_VISA_CC_NUMBER, Long.class);
                    break;
                case MASTER:
                    ccNumber = staticJdbcTemplate.queryForObject(SELECT_MAX_MASTER_CC_NUMBER, Long.class);
                    break;
                case DISCOVER:
                    ccNumber = staticJdbcTemplate.queryForObject(SELECT_MAX_DISCOVER_CC_NUMBER, Long.class);
                    break;
                default:
                    ccNumber = -1;
            }
            logger.info("Max Generated CC Number in DB for {} : {}", cardType, ccNumber);
        } catch (Exception e) {
            logger.error("Exception while getting last cc number for " + cardType, e);
        }
        return ccNumber;
    }

    public static boolean setCCNumber(long ccnumber, CardType cardType) {
        int updateCount = 0;
        try {
            switch (cardType) {
                case AMEX:
                    updateCount = staticJdbcTemplate.update(INSERT_AMEX_CC_NUMBER, ccnumber);
                    break;
                case VISA:
                    updateCount = staticJdbcTemplate.update(INSERT_VISA_CC_NUMBER, ccnumber);
                    break;
                case MASTER:
                    updateCount = staticJdbcTemplate.update(INSERT_MASTER_CC_NUMBER, ccnumber);
                    break;
                case DISCOVER:
                    updateCount = staticJdbcTemplate.update(INSERT_DISCOVER_CC_NUMBER, ccnumber);
                    break;
                default:
                    updateCount = 0;
            }
            logger.info("New CC Number generated for {} : {}", cardType, ccnumber);
        } catch (DuplicateKeyException e) {
            logger.error("DuplicateKeyException for {} : {}", cardType, ccnumber);
        } catch (Exception e) {
            logger.error("Exception while inserting cc number for " + cardType, e);
        }
        return updateCount > 0;
    }
}
