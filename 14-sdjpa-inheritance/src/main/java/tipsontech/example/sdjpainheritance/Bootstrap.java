package tipsontech.example.sdjpainheritance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpainheritance.domain.joined.ElectricGuitar;
import tipsontech.example.sdjpainheritance.repositories.ElectricGuitarRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private ElectricGuitarRepository electricGuitarRepository;

    @Override
    public void run(String... args) throws Exception {
        ElectricGuitar eg = new ElectricGuitar();

        eg.setNumberOfStrings(6);
        eg.setNumberOfPickups(2);

        electricGuitarRepository.save(eg);
    }
}
