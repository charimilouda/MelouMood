package ma.meloumyy.melou_recepe.repo;

import ma.meloumyy.melou_recepe.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    List<Evaluation> findByRecepe_Id(Long id);

}