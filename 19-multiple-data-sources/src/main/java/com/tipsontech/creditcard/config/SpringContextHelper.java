package com.tipsontech.creditcard.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * The SpringContextHelper class is a utility class that provides static access to the Spring ApplicationContext. Here's why it's useful:
 *
 * Static Access to Spring Beans: It allows you to get Spring-managed beans from anywhere in your application, even in classes that aren't managed by Spring (like POJOs, utility classes, or entities).
 * Implementation of ApplicationContextAware: By implementing this interface, Spring automatically injects the application context when the application starts up.
 * Common Use Case: In your project, it's likely being used to access Spring beans from within JPA entity classes or other non-Spring managed components where dependency injection isn't directly available.
 * For example, in your credit card processing application, you might need to access services (like EncryptionServiceImpl) from within your JPA entities or callbacks, but since entities are managed by JPA, not Spring, you can't use @Autowired. The
 * SpringContextHelper provides a way to access those Spring-managed services when needed.
 *
 * However, it's worth noting that while this pattern works, it's generally considered better practice to avoid this approach when possible, as it can make your code harder to test and reason about. The "proper" dependency injection approach is usually preferred when feasible.
 */

@Component
public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
