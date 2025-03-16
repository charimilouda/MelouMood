import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecettesListComponent } from './components/recettes-list/recettes-list.component';
import { RecetteDetailsComponent } from './components/recette-details/recette-details.component';

const routes: Routes = [
  { path: '', component: RecettesListComponent }, // Route pour la page d'accueil
  { path: 'recettes', component: RecettesListComponent }, // Route pour la liste des recettes
  { path: 'recette/:id', component: RecetteDetailsComponent }, // Route pour les détails de la recette
  
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
