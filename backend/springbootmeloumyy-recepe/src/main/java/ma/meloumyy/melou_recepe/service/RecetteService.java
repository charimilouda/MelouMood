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
public class RecetteService {

    @Autowired
    private RecetteRepository recetteRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;

    public List<Recette> getAllRecettes() {
        List<Recette> recettes = recetteRepository.findAll();
        for (Recette recette : recettes) {
            recette.setAverageRating(calculateAverageRating(recette.getId()));
        }
        return recettes;
    }

    public Optional<Recette> getRecetteById(Long id) {
        Optional<Recette> recette = recetteRepository.findById(id);
        recette.ifPresent(r -> r.setAverageRating(calculateAverageRating(id)));
        return recette;
    }



    public void deleteRecette(Long id) {
        recetteRepository.deleteById(id);
    }

    // Méthode pour rechercher des recettes par titre contenant un mot-clé
    public List<Recette> findByTitreContaining(String keyword) {
        return recetteRepository.findByTitreContaining(keyword);
    }


    //Evaluation de la recette
    private double calculateAverageRating(Long recetteId) {
        List<Evaluation> evaluations = evaluationRepository.findByRecepe_Id(recetteId);
        if (evaluations.isEmpty()) return 0.0;

        double total = evaluations.stream().mapToInt(Evaluation::getNote).sum();
        return total / evaluations.size();
    }

    public Recette saveRecette(Recette recette) {
        return recetteRepository.save(recette);
    }



}
