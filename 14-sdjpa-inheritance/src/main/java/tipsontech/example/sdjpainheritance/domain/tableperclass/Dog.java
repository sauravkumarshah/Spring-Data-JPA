package tipsontech.example.sdjpainheritance.domain.tableperclass;

import jakarta.persistence.*;

@Entity
public class Dog extends Mammal {

    private String breed;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
