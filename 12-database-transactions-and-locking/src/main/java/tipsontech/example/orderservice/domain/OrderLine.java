package tipsontech.example.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity {

    @Version
    private Integer version;
    private Integer quantityOrdered;

    @ManyToOne
    private OrderHeader orderHeader;
    @ManyToOne
    private Product product;

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine orderLine)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getQuantityOrdered(), orderLine.getQuantityOrdered()) && Objects.equals(getOrderHeader(), orderLine.getOrderHeader()) && Objects.equals(getProduct(), orderLine.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuantityOrdered(), getProduct());
    }
}
