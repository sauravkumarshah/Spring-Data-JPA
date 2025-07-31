package tipsontech.example.orderservice.domain;

import jakarta.persistence.Entity;

@Entity
public class OrderHeader extends BaseEntity {

    private String customer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
