package ma.meloumyy.melou_recepe.repo;

import ma.meloumyy.melou_recepe.entity.Favori;
import ma.meloumyy.melou_recepe.entity.FavoriId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface FavoriRepository extends JpaRepository<Favori, FavoriId> {
}
