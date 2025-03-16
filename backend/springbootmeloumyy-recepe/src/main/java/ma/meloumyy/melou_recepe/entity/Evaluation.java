package ma.meloumyy.melou_recepe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_recette", nullable = false,referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_post4"))
    private Recette recepe;

    @ManyToOne
    @JoinColumn(name = "id_auteur", nullable = false,referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_post3"))
    private  Utilisateur auteur;
    @Column(name = "note")
    private short note;
    @CreationTimestamp
    @Column(name = "date_evaluation")
    private Date dateEvaluation;



    public int getRating() {
        return note;
    }

    // Setter pour rating
    public void setRating(int rating) {
        this.note = note;
    }
    public Recette getRecette() {
        return recepe;
    }

    public void setRecette(Recette recette) {
        this.recepe = recette;
    }

}
