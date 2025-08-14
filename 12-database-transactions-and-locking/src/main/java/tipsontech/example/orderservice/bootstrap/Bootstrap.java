package tipsontech.example.orderservice.bootstrap;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tipsontech.example.orderservice.domain.OrderHeader;
import tipsontech.example.orderservice.repositories.OrderHeaderRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private BootstrapOrderService bootstrapOrderService;

//    @Autowired
//    private OrderHeaderRepository orderHeaderRepository;
//
//    @Transactional
//    public void readOrderData() {
//        OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();
//
//        orderHeader.getOrderLines().forEach(orderLine -> {
//            System.out.println(orderLine.getProduct().getDescription());
//
//            orderLine.getProduct().getCategories().forEach(cat -> {
//                System.out.println(cat.getDescription());
//            });
//        });
//    }

    // without transactional annotation hibernate will throw an exception lazy initialization error
    @Override
    public void run(String... args) throws Exception {

        bootstrapOrderService.readOrderData();

    }
}
