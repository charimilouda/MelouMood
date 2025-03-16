package ma.meloumyy.melou_recepe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "commentaire")
@Getter
@Setter
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "IdRecette", nullable = false,referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_post2"))
    private Recette recepe;

    @ManyToOne
    @JoinColumn(name = "IdAuteur", nullable = false,referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_post1"))
    private  Utilisateur auteur;
    @Column(name = "Texte",columnDefinition = "TEXT")
    private String texte;
    @Column(name = "DatePublication")
    @CreationTimestamp
    private Date datePublication;
}
