package com.tipsontech.creditcard.repositories.creditcard;

import com.tipsontech.creditcard.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}