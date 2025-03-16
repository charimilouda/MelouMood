package ma.meloumyy.melou_recepe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Nom")
    private String nom;
    @Column(name = "Prenom")
    private  String prenom;
    @Column(name = "Email")
    private String email;
    @Column(name = "MotDePasse")
    private String motDePasse;
    @Column(name = "Bio",columnDefinition = "TEXT")
    private String bio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auteur")
    @JsonIgnore
    private Set<Commentaire> commentaires;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auteur")
    @JsonIgnore
    private Set<Recette> recettes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auteur")
    @JsonIgnore
    private Set<Evaluation> evaluations;




    //getters and setters

}
