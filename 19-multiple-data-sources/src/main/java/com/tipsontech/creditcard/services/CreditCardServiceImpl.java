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
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow();
        CreditCardHolder creditCardHolder = creditCardHolderRepository.findByCreditCardId(id).orElseThrow();
        CreditCardPAN creditCardPAN = creditCardPANRepository.findByCreditCardId(id).orElseThrow();

        creditCard.setFirstName(creditCardHolder.getFirstName());
        creditCard.setLastName(creditCardHolder.getLastName());
        creditCard.setZipCode(creditCardHolder.getZipCode());
        creditCard.setCreditCardNumber(creditCardPAN.getCreditCardNumber());

        return creditCard;
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