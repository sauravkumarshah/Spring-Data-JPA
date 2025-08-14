package tipsontech.example.orderservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.orderservice.domain.Product;
import tipsontech.example.orderservice.domain.ProductStatus;
import tipsontech.example.orderservice.services.ProductService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {ProductService.class})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    public void testGetCategory(){
        Product product = productRepository.findByDescription("Product 1").get();
        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productRepository.save(product);

        Product fetchedProduct = productRepository.getById(savedProduct.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getDescription());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getLastModifiedDate());
    }

    @Test
    void addAndUpdateProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

//        Product savedProduct = productRepository.saveAndFlush(product);
        Product savedProduct = productService.saveProduct(product);

//        savedProduct.setQuantityOnHand(25);

//        Product savedProduct2 = productRepository.saveAndFlush(savedProduct);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println(savedProduct2.getQuantityOnHand());
    }
}
