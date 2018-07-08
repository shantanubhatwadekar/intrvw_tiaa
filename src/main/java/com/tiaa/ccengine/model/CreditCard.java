package com.tiaa.ccengine.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CreditCard {

    @Getter
    @Setter
    long cardNumber;

    @Getter
    @Setter
    LocalDate expDate;
}
