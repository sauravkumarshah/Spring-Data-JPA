package com.tipsontech.creditcard.repositories.cardholder;

import com.tipsontech.creditcard.domain.creditcardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}