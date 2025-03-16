package ma.meloumyy.melou_recepe.repo;

import ma.meloumyy.melou_recepe.entity.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("http://localhost:4200")
public interface RecetteRepository extends JpaRepository<Recette,Long> {
    // Recherche des recettes par titre contenant un mot-clé ( je pense que c'est pas besoin, j'ai developpé cela avec
    //angular seulement
    List<Recette> findByTitreContaining(String keyword);
}
