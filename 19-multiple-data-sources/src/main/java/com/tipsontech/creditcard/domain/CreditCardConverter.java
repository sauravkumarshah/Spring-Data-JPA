package com.tipsontech.creditcard.domain;

import com.tipsontech.creditcard.config.SpringContextHelper;
import com.tipsontech.creditcard.services.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


/**
 *
 In your CreditCardConverter Spring-Data-JPA/18-jpa-callbacks/src/main/java/com/tipsontech/creditcard/domain/CreditCardConverter.java class, you need to use the EncryptionService to decrypt the credit card number. Normally in Spring, you'd use @Autowired to inject this dependency. However, there's a catch:

 The Problem:
 CreditCardConverter is instantiated by JPA (Java Persistence API), not by Spring Therefore, Spring's dependency injection (like @Autowired) won't work here The @Convert annotation is a JPA annotation, and JPA doesn't know about Spring's dependency injection.

 The Solution - SpringContextHelper:

 private EncryptionService getEncryptionService() {
    return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
 }

 This method uses SpringContextHelper to manually get the EncryptionService from Spring's context. It works because SpringContextHelper

 Spring-Data-JPA/18-jpa-callbacks/src/main/java/com/tipsontech/creditcard/config/SpringContextHelper.java has a static reference to the Spring ApplicationContext

 Why This Works:

 SpringContextHelper
 Spring-Data-JPA/18-jpa-callbacks/src/main/java/com/tipsontech/creditcard/config/SpringContextHelper.java is a Spring-managed component (@Component)

 When Spring starts, it creates this bean and calls setApplicationContext()
 This stores the ApplicationContext in a static variable

 Now any class can access Spring beans, even if it's not managed by Spring
 */
@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {


    @Override
    public String convertToDatabaseColumn(String attribute) {
        return getEncryptionService().encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return getEncryptionService().decrypt(dbData);
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
