package tipsontech.example.orderservice.bootstrap;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tipsontech.example.orderservice.domain.Customer;
import tipsontech.example.orderservice.domain.OrderHeader;
import tipsontech.example.orderservice.domain.Product;
import tipsontech.example.orderservice.domain.ProductStatus;
import tipsontech.example.orderservice.repositories.CustomerRepository;
import tipsontech.example.orderservice.repositories.OrderHeaderRepository;
import tipsontech.example.orderservice.services.ProductService;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private BootstrapOrderService bootstrapOrderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductService productService;

    private void updateProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println("Updated Qty: " + savedProduct2.getQuantityOnHand());
    }

    // without transactional annotation hibernate will throw an exception lazy initialization error
    @Override
    public void run(String... args) throws Exception {

        updateProduct();

        bootstrapOrderService.readOrderData();

        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Version is : " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("Version is : " + savedCustomer2.getVersion());

        savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        System.out.println("Version is : " + savedCustomer3.getVersion());

        customerRepository.delete(savedCustomer3);

    }
}
