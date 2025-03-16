package ma.meloumyy.melou_recepe.service;

import ma.meloumyy.melou_recepe.entity.Evaluation;
import ma.meloumyy.melou_recepe.entity.Recette;
import ma.meloumyy.melou_recepe.repo.EvaluationRepository;
import ma.meloumyy.melou_recepe.repo.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Optional<Evaluation> getEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }
    @Autowired
    private RecetteRepository recetteRepository;

    public void saveEvaluation(Long recetteId, Evaluation evaluation) {
        Recette recette = recetteRepository.findById(recetteId).orElse(null);
        if (recette != null) {
            evaluation.setRecette(recette);
            evaluationRepository.save(evaluation);
            recette.addEvaluation(evaluation);
            recetteRepository.save(recette);
        }
    }
}
