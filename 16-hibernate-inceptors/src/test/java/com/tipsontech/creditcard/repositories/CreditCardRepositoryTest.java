package com.tipsontech.creditcard.repositories;

import com.tipsontech.creditcard.domain.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    final String CREDIT_CARD_NUMBER = "1234-1234-1234-1234";
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Test
    public void testSaveAndStoreCreditCard(){
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCC = creditCardRepository.save(creditCard);

        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).orElse(null);

        assertNotNull(fetchedCC);
        assertThat(savedCC.getCreditCardNumber(), equalTo(fetchedCC.getCreditCardNumber()));
    }

}