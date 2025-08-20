package com.tipsontech.creditcard;

import com.tipsontech.creditcard.domain.creditcard.CreditCard;
import  com.tipsontech.creditcard.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private CreditCardService creditCardService;

	@Test
	void testSaveAndGetCreditCard() {
		CreditCard creditCard = CreditCard.builder()
				.firstName("John")
				.lastName("Thompson")
				.zipCode("12345")
				.creditCardNumber("1234567890123456")
				.cvv("123")
				.expirationDate("12/26")
				.build();

		CreditCard savedCreditCard = creditCardService.saveCreditCard(creditCard);

		assertThat(savedCreditCard).isNotNull();
		assertThat(savedCreditCard.getId()).isNotNull();
		assertThat(savedCreditCard.getCreditCardNumber()).isNotNull();

		System.out.println(savedCreditCard);
	}

	@Test
	void contextLoads() {
	}

}
