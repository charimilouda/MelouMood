export class Commentaire {
  id?: number; // L'id peut être optionnel lors de la création
  texte: string;
  datePublication?: string; // La date de publication peut être optionnelle lors de la création
  recetteId: number; // ID de la recette associée
  auteurId: number; // ID de l'auteur

  constructor(texte: string, recetteId: number, auteurId: number, id?: number, datePublication?: string) {
    this.texte = texte;
    this.recetteId = recetteId;
    this.auteurId = auteurId;
    if (id) this.id = id;
    if (datePublication) this.datePublication = datePublication;
  }
}
