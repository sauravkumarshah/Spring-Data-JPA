package tipsontech.example.sdjpaintro.domain.composite;

import java.io.Serializable;
import java.util.Objects;

public class NameId implements Serializable {

    private String firstName;
    private String lastName;

    public NameId() {   }
    public NameId(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NameId nameId)) return false;
        return Objects.equals(getFirstName(), nameId.getFirstName()) && Objects.equals(getLastName(), nameId.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}
