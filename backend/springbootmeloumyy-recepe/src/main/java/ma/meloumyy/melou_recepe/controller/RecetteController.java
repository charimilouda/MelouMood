package ma.meloumyy.melou_recepe.controller;
import ma.meloumyy.melou_recepe.entity.Recette;
import ma.meloumyy.melou_recepe.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes CORS depuis http://localhost:4200"
@RequestMapping("/api/recettes")
public class RecetteController {

    @Autowired
    private RecetteService recetteService;

    @GetMapping
    public List<Recette> getAllRecettes() {
        return recetteService.getAllRecettes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable Long id) {
        Optional<Recette> recette = recetteService.getRecetteById(id);
        return recette.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Recette createRecette(@RequestBody Recette recette) {
        return recetteService.saveRecette(recette);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecette(@PathVariable Long id) {
        if (recetteService.getRecetteById(id).isPresent()) {
            recetteService.deleteRecette(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour rechercher des recettes par titre contenant un mot-clé
    @GetMapping("/search")
    public List<Recette> searchRecettes(@RequestParam String keyword) {
        return recetteService.findByTitreContaining(keyword);
    }

    //pour l'evaluation je pense
    @PutMapping("/{id}")
    public ResponseEntity<Recette> updateRecette(@PathVariable Long id, @RequestBody Recette recetteDetails) {
        Optional<Recette> recette = recetteService.getRecetteById(id);
        if (recette.isPresent()) {
            Recette updatedRecette = recette.get();
            updatedRecette.setTitre(recetteDetails.getTitre());
            updatedRecette.setPhotos(recetteDetails.getPhotos());
            updatedRecette.setIngredients(recetteDetails.getIngredients());
            updatedRecette.setEtapes(recetteDetails.getEtapes());
            updatedRecette.setAuteur(recetteDetails.getAuteur());
            updatedRecette.setCommentaires(recetteDetails.getCommentaires());
            updatedRecette.setEvaluations(recetteDetails.getEvaluations());
            recetteService.saveRecette(updatedRecette);
            return ResponseEntity.ok(updatedRecette);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
