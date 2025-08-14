package tipsontech.example.orderservice.services;

import tipsontech.example.orderservice.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateQOH(Long id, Integer quantityOnHand);
}
