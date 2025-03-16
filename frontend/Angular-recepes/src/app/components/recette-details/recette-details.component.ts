import { Component, OnInit } from '@angular/core';
import { RecetteService } from '../../services/recette.service';
import { Recette } from '../../common/recette';
import { Commentaire } from '../../common/commentaire'; // Importer l'interface Commentaire si vous en avez une
import { CommentaireService } from '../../services/commentaire.service'; // Importer le service pour les commentaires
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-recette-details',
  templateUrl: './recette-details.component.html',
  styleUrls: ['./recette-details.component.css']
})
export class RecetteDetailsComponent implements OnInit {
  recette: Recette | null = null;
  commentaires: Commentaire[] = [];
  newComment: string = '';
  commentaire: Commentaire = {
    auteurId: 1,
    recetteId: 3,
    texte: '',
    datePublication: new Date().toISOString() // Utilisez la date actuelle ou une valeur par défaut
  };
  constructor(
    private recetteService: RecetteService,
    private route: ActivatedRoute,
    private router: Router,
    private commentaireService: CommentaireService
  ) {}

  ngOnInit(): void {
    this.getRecetteDetails();
    this.getCommentaires();
  }

  getRecetteDetails(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.recetteService.getRecetteById(id).subscribe(
      data => this.recette = data,
      error => console.error('Erreur lors de la récupération des détails de la recette:', error)
    );
  }

  getCommentaires(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.commentaireService.getCommentairesByRecetteId(id).subscribe(
      data => {
        console.log('Commentaires reçus:', data); // Debug: Vérifiez les données reçues
        this.commentaires = data;
      },
      error => console.error('Erreur lors de la récupération des commentaires', error)
    );
  }

  submitComment() {
    // Log des données envoyées pour vérifier la clé correcte
    console.log('Données envoyées:', {
      auteurId: this.commentaire.auteurId,
      recetteId: this.commentaire.recetteId, // Clé corrigée ici
      texte: this.commentaire.texte,
      datePublication: new Date().toISOString() // Assurez-vous que la date est au format ISO
    });
  
    // Appel au service pour ajouter un commentaire
    this.commentaireService.ajouterCommentaire({
      auteurId: this.commentaire.auteurId,
      recetteId: this.commentaire.recetteId, // Clé corrigée ici
      texte: this.commentaire.texte,
      datePublication: new Date().toISOString() // Assurez-vous que la date est au format ISO
    }).subscribe(
      response => {
        console.log('Réponse du serveur:', response);
        // Rafraîchir la liste des commentaires ou mettre à jour l'interface utilisateur
      },
      error => {
        console.error('Erreur lors de l\'ajout du commentaire:', error);
      }
    );
  }
  
  
  
  
  
  

  back(): void {
    this.router.navigate(['/recettes']);
  }
  
  rateRecipe(rating: number): void {
    if (this.recette) {
      this.recetteService.rateRecette(this.recette.id, rating).subscribe(
        () => {
          // Mettre à jour la recette après l'évaluation
          this.getRecetteDetails();
        },
        (error) => console.error('Erreur lors de la notation de la recette:', error)
      );
    }
  }

  getStarClass(index: number, averageRating: number | undefined): string {
    if (averageRating === undefined || averageRating === null || averageRating === 0) {
      return 'fa-star-o'; // Étoile vide pour les notes nulles
    }
  
    const fullStars = Math.floor(averageRating);
    const hasHalfStar = averageRating % 1 !== 0;
  
    if (index < fullStars) {
      return 'fa-star'; // Étoile pleine
    } else if (index === fullStars && hasHalfStar) {
      return 'fa-star-half-alt'; // Étoile à moitié pleine
    } else {
      return 'fa-star-o'; // Étoile vide
    }
  }
  
  

}
