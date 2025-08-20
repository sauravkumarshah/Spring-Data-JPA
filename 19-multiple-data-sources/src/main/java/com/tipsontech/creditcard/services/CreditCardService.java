package com.tipsontech.creditcard.services;

import com.tipsontech.creditcard.domain.creditcard.CreditCard;

public interface CreditCardService {

    CreditCard getCreditCardById(Long id);

    CreditCard saveCreditCard(CreditCard creditCard);
}