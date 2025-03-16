import { Component, OnInit } from '@angular/core';
import { RecetteService } from '../../services/recette.service';
import { Recette } from '../../common/recette';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recettes-list',
  templateUrl: './recettes-list.component.html',
  styleUrls: ['./recettes-list.component.css']
})
export class RecettesListComponent implements OnInit {
  searchQuery: string = '';
  recettes: Recette[] = [];
  totalPages: number = 1; // Initialiser avec une valeur par défaut
  page: number = 1;
  itemsPerPage: number = 10; // Nombre d'items par page

  constructor(private recetteService: RecetteService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.listRecettes();
  }

  listRecettes() {
    this.recetteService.getRecettesList().subscribe(
      (data) => {
        this.recettes = data;
        console.log('Recettes:', this.recettes);
        this.calculateTotalPages(); // Calculer le nombre total de pages après avoir chargé les recettes
      },
      (error) => {
        console.error('Erreur lors de la récupération des recettes:', error);
      }
    );
  }
  //pour la pagination
  calculateTotalPages() {
    this.totalPages = Math.ceil(this.recettes.length / this.itemsPerPage);
  }

  search() {
    // Logique de recherche
    this.calculateTotalPages(); // Recalcule le nombre total de pages lors de la recherche
  }

  showDetails(recette: Recette): void {
    // Implémentez la logique pour afficher les détails de la recette
    this.router.navigate(['/recette', recette.id]);
  }
  //pour afficher l'evaluation des recettes
  getStarClass(index: number, averageRating: number): string {
    // Vérifier si aucune évaluation n'existe
    if (averageRating === null || averageRating === undefined || averageRating === 0) {
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
