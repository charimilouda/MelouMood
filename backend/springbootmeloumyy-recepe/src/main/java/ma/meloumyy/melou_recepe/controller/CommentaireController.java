package ma.meloumyy.melou_recepe.controller;

import ma.meloumyy.melou_recepe.entity.Commentaire;
import ma.meloumyy.melou_recepe.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes CORS depuis http://localhost:4200
@RequestMapping("/api/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @GetMapping
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commentaire> getCommentaireById(@PathVariable(value = "id") Long id) {
        Optional<Commentaire> commentaire = commentaireService.getCommentaireById(id);
        return commentaire.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(@PathVariable(value = "id") Long id, @RequestBody Commentaire commentaireDetails) {
        Commentaire updatedCommentaire = commentaireService.updateCommentaire(id, commentaireDetails);
        return ResponseEntity.ok(updatedCommentaire);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable(value = "id") Long id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.noContent().build();
    }
    // Mappage pour obtenir les commentaires par ID de recette
    @GetMapping("/recette/{recetteId}")
    public List<Commentaire> getCommentairesByRecetteId(@PathVariable Long recetteId) {
        return commentaireService.getCommentairesByRecetteId(recetteId);
    }

    // Mappage pour ajouter un nouveau commentaire
    @PostMapping
    public ResponseEntity<?> ajouterCommentaire(@RequestBody Commentaire commentaire) {
        try {
            if (commentaire.getRecepe() == null || commentaire.getRecepe().getId() == null) {
                return ResponseEntity.badRequest().body("Recette must not be null");
            }
            if (commentaire.getAuteur() == null || commentaire.getAuteur().getId() == null) {
                return ResponseEntity.badRequest().body("Auteur must not be null");
            }

            Commentaire savedCommentaire = commentaireService.ajouterCommentaire(commentaire);
            return ResponseEntity.ok(savedCommentaire);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }





}
