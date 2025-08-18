package com.tipsontech.creditcard.repositories.pan;

import com.tipsontech.creditcard.domain.creditcardpan.CreditCardPAN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {
}