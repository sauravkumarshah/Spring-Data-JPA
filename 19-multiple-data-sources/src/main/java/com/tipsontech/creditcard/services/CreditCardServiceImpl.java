package com.tipsontech.creditcard.services;

import com.tipsontech.creditcard.domain.creditcard.CreditCard;
import com.tipsontech.creditcard.domain.creditcardholder.CreditCardHolder;
import com.tipsontech.creditcard.domain.creditcardpan.CreditCardPAN;
import com.tipsontech.creditcard.repositories.cardholder.CreditCardHolderRepository;
import com.tipsontech.creditcard.repositories.creditcard.CreditCardRepository;
import com.tipsontech.creditcard.repositories.pan.CreditCardPANRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardPANRepository creditCardPANRepository;
    private final CreditCardHolderRepository creditCardHolderRepository;
    private final CreditCardRepository creditCardRepository;

    @Override
    public CreditCard getCreditCardById(Long id) {

        return null;
    }

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        CreditCard savedCC = creditCardRepository.save(creditCard);
        savedCC.setFirstName(creditCard.getFirstName());
        savedCC.setLastName(creditCard.getLastName());
        savedCC.setZipCode(creditCard.getZipCode());
        savedCC.setCreditCardNumber(creditCard.getCreditCardNumber());

        creditCardHolderRepository.save(CreditCardHolder.builder()
                .creditCardId(savedCC.getId())
                .firstName(creditCard.getFirstName())
                .lastName(creditCard.getLastName())
                .zipCode(creditCard.getZipCode())
                .build());

        creditCardPANRepository.save(CreditCardPAN.builder()
                .creditCardId(savedCC.getId())
                .creditCardNumber(creditCard.getCreditCardNumber())
                .build());

        return savedCC;
    }
}