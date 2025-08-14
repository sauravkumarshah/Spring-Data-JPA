package tipsontech.example.orderservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderHeaderTest {

    @Test
    public void testEquals() {
        OrderHeader orderHeader1 = new OrderHeader();
        orderHeader1.setId(1L);

        OrderHeader orderHeader2 = new OrderHeader();
        orderHeader2.setId(1L);

        assertEquals(orderHeader1, orderHeader2);
    }

    @Test
    public void testNotEquals() {
        OrderHeader orderHeader1 = new OrderHeader();
        orderHeader1.setId(1L);

        OrderHeader orderHeader2 = new OrderHeader();
        orderHeader2.setId(3L);

        assertNotEquals(orderHeader1, orderHeader2);
    }

}