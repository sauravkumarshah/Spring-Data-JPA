package tipsontech.example.sdjpainheritance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpainheritance.domain.joined.ElectricGuitar;

public interface ElectricGuitarRepository extends JpaRepository<ElectricGuitar, Integer> {
}
