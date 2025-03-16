package ma.meloumyy.melou_recepe.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "favori")
@Getter
@Setter
public class Favori {

    @EmbeddedId
    private FavoriId id;

    @ManyToOne
    @MapsId("idUtilisateur")  // Utilisé pour indiquer que la clé étrangère correspond à la partie idUtilisateur de la clé primaire composite
    @JoinColumn(name = "IdUtilisateur", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_favori_utilisateur"))
    private Utilisateur utilisateur;

    @ManyToOne
    @MapsId("idRecette")  // Utilisé pour indiquer que la clé étrangère correspond à la partie idRecette de la clé primaire composite
    @JoinColumn(name = "IdRecette", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_favori_recette"))
    private Recette recette;

    @Column(name = "DateAjout", nullable = false, columnDefinition = "DATETIME")
    private Date dateAjout;
}
