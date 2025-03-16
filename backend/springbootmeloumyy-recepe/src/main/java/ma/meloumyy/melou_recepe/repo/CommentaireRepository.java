package ma.meloumyy.melou_recepe.repo;

import ma.meloumyy.melou_recepe.entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
    List<Commentaire> findByRecepeId(Long recetteId);}
