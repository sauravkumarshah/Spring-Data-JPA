package com.tipsontech.creditcard.repositories;

import com.tipsontech.creditcard.domain.CreditCard;
import com.tipsontech.creditcard.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    final String CREDIT_CARD_NUMBER = "1234-1234-1234-1234";

    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Test
    public void testEncryptAndDecryptCreditCardNumber(){
        String encryptedCreditCardNumber = encryptionService.encrypt(CREDIT_CARD_NUMBER);
        String decryptedCreditCardNumber = encryptionService.decrypt(encryptedCreditCardNumber);
        assertThat(CREDIT_CARD_NUMBER, equalTo(decryptedCreditCardNumber));
    }

    @Test
    public void testSaveAndStoreCreditCard(){
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCC = creditCardRepository.save(creditCard);

        System.out.println("Getting CC from database : " + creditCard.getCreditCardNumber());

        System.out.println("CC At Rest");

        System.out.println("CC Encrypted : " + encryptionService.encrypt(CREDIT_CARD_NUMBER));

        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).orElse(null);

        assertNotNull(fetchedCC);
        assertThat(savedCC.getCreditCardNumber(), equalTo(fetchedCC.getCreditCardNumber()));
    }

}