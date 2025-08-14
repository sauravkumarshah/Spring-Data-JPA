package tipsontech.example.orderservice.bootstrap;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tipsontech.example.orderservice.domain.OrderHeader;
import tipsontech.example.orderservice.repositories.OrderHeaderRepository;

@Service
public class BootstrapOrderService {

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Transactional
    public void readOrderData() {
        OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();

        orderHeader.getOrderLines().forEach(orderLine -> {
            System.out.println(orderLine.getProduct().getDescription());

            orderLine.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });
        });
    }
}
