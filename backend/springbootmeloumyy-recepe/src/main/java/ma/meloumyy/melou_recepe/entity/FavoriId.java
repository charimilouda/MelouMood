package ma.meloumyy.melou_recepe.entity;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavoriId implements Serializable {

    private Long idUtilisateur;
    private Long idRecette;

    // Constructeurs
    public FavoriId() {}

    public FavoriId(Long idUtilisateur, Long idRecette) {
        this.idUtilisateur = idUtilisateur;
        this.idRecette = idRecette;
    }

    // Getters et Setters
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Long getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(Long idRecette) {
        this.idRecette = idRecette;
    }

    // equals et hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriId favoriId = (FavoriId) o;
        return Objects.equals(idUtilisateur, favoriId.idUtilisateur) &&
                Objects.equals(idRecette, favoriId.idRecette);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idRecette);
    }
}
