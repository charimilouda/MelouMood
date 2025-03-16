import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recette } from '../common/recette';
import { Commentaire } from '../common/commentaire';

@Injectable({
  providedIn: 'root'
})
export class RecetteService {
  private baseUrl = 'http://localhost:8090/api/recettes';

  constructor(private httpClient: HttpClient) { }
  // Méthode pour obtenir la liste des recettes
  getRecettesList(): Observable<Recette[]> {
    return this.httpClient.get<Recette[]>(this.baseUrl);
  }
  // Méthode pour obtenir une recette par ID
  getRecetteById(id: number): Observable<Recette> {
    return this.httpClient.get<Recette>( `${this.baseUrl}/${id}`);
  }
  
  // Méthode pour évaluer une recette
  rateRecette(id: number, rating: number): Observable<void> {
    return this.httpClient.post<void>(`${this.baseUrl}/${id}/rate`, { rating });
  }

  
  
}