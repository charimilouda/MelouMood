package ma.meloumyy.melou_recepe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recette")
@Getter
@Setter
public class Recette {
    @Id //pour dire que l'attribut is est une clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pour dire que id est autoincrement
    @Column(name = "Id")//pour faire le mapping : c'est a dire pour lier cet attribut avec la colonne id de la table reectte de la base de données
    private Long id;
    @Column(name = "Titre")
    private String titre;
    @Column(name = "Photos")
    private String photos;
    @Column(name = "Ingredients",columnDefinition = "TEXT")
    private String ingredients;
    @Column(name = "Etapes",columnDefinition = "TEXT")
    private String etapes;
    @Column(name = "DateCreation")
    @CreationTimestamp
    private Date dateCreation;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "IdAuteur", nullable = false,referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_post"))
    private  Utilisateur auteur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recepe")
    @JsonIgnore // Ignorer les commentaires pour éviter les boucles infinies
    private Set<Commentaire> commentaires;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recepe")
    @JsonIgnore
    private Set<Evaluation> evaluations;




    //pour l'évaluation de la recette
    @Transient
    private double averageRating;




    //pour que l'utilisateur arrive a evaluer une recette
    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        updateAverageRating();
    }
    private void updateAverageRating() {
        double total = evaluations.stream().mapToInt(Evaluation::getRating).sum();
        this.averageRating = evaluations.size() > 0 ? total / evaluations.size() : 0;
    }



}
