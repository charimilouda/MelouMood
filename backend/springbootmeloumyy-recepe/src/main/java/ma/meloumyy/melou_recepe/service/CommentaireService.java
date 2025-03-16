package ma.meloumyy.melou_recepe.service;
import ma.meloumyy.melou_recepe.entity.Commentaire;
import ma.meloumyy.melou_recepe.entity.Recette;
import ma.meloumyy.melou_recepe.entity.Utilisateur;
import ma.meloumyy.melou_recepe.repo.CommentaireRepository;
import ma.meloumyy.melou_recepe.repo.RecetteRepository;
import ma.meloumyy.melou_recepe.repo.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private RecetteRepository recetteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    public Optional<Commentaire> getCommentaireById(Long id) {
        return commentaireRepository.findById(id);
    }

    public Commentaire saveCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    public void deleteCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }

    public Commentaire ajouterCommentaire(Commentaire commentaire) {
        if (commentaire.getRecepe() == null || commentaire.getRecepe().getId() == null) {
            throw new IllegalArgumentException("Recette must not be null");
        }
        if (commentaire.getAuteur() == null || commentaire.getAuteur().getId() == null) {
            throw new IllegalArgumentException("Auteur must not be null");
        }

        // Charger les entités associées
        Recette recette = recetteRepository.findById(commentaire.getRecepe().getId())
                .orElseThrow(() -> new IllegalArgumentException("Recette not found"));
        Utilisateur auteur = utilisateurRepository.findById(commentaire.getAuteur().getId())
                .orElseThrow(() -> new IllegalArgumentException("Auteur not found"));

        commentaire.setRecepe(recette);
        commentaire.setAuteur(auteur);

        return commentaireRepository.save(commentaire);
    }
    public List<Commentaire> getCommentairesByRecetteId(Long recetteId) {
        return commentaireRepository.findByRecepeId(recetteId);
    }
    public Commentaire updateCommentaire(Long id, Commentaire commentaireDetails) {
        Commentaire commentaire = commentaireRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Commentaire not found for this id :: " + id));

        commentaire.setTexte(commentaireDetails.getTexte());
        commentaire.setRecepe(commentaireDetails.getRecepe());
        commentaire.setAuteur(commentaireDetails.getAuteur());

        final Commentaire updatedCommentaire = commentaireRepository.save(commentaire);
        return updatedCommentaire;
    }
}
