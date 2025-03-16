import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Commentaire } from '../common/commentaire'; // Assurez-vous que Commentaire est défini



@Injectable({
  providedIn: 'root'
})
export class CommentaireService {
  private apiUrl = 'http://localhost:8090/api/commentaires';
  // Définition des en-têtes HTTP
  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {}

  getCommentairesByRecetteId(recetteId: number): Observable<Commentaire[]> {
    return this.http.get<Commentaire[]>(`${this.apiUrl}/recette/${recetteId}`);
  }

  // Méthode pour ajouter un commentaire
  ajouterCommentaire(commentaire: Commentaire): Observable<Commentaire> {
    return this.http.post<Commentaire>(this.apiUrl, commentaire, { headers: this.headers });
  }
  
}