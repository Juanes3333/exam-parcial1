package repos;

import com.example.model.Sightings;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SightingsRepository {
    private List<Sightings> sightings;

    public SightingsRepository() {
        this.sightings = new ArrayList<>();
        this.sightings.add(new Sightings(1,"1A","piedra","encontrado en el suelo","piedritus", "2026-09-06 2:00PM", 1,1));
        this.sightings.add(new Sightings(2,"2A","pajaro","visto volando","pajaritus","2026-09-06 4:00PM", 2,1));
        this.sightings.add(new Sightings(3,"3A","arbol","encima de una montaña","arbulitus","2026-09-06 6:00PM",3,1));
    }

    public void addSightings(Sightings Sightings){
        sightings.add(Sightings);
    }

    public List<Sightings> findAll (){
        return sightings;
    }

    public Sightings findById(Integer id){
        for (Sightings Sightings : sightings){
            if (Sightings.getId().equals(id)){
                return Sightings;
            }
        }
        return null;
    }
}
