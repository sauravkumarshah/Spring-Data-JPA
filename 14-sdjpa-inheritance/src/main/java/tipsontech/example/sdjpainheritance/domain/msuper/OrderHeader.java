package tipsontech.example.sdjpainheritance.domain.msuper;

import jakarta.persistence.*;
@Entity
public class OrderHeader extends  BaseEntity {

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
