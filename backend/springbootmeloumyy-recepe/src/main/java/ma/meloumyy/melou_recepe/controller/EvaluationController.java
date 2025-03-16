package ma.meloumyy.melou_recepe.controller;

import ma.meloumyy.melou_recepe.entity.Evaluation;
import ma.meloumyy.melou_recepe.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes CORS depuis http://localhost:4200
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable(value = "id") Long id) {
        Optional<Evaluation> evaluation = evaluationService.getEvaluationById(id);
        return evaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{recetteId}")
    public void addEvaluation(@PathVariable Long recetteId, @RequestBody Evaluation evaluation) {
        evaluationService.saveEvaluation(recetteId, evaluation);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable(value = "id") Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}
