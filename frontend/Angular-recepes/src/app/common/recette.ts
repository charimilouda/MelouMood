export class Recette {
    constructor(
      public id: number,
      public titre: string,
      public photos: string,
      public ingredients: string,
      public etapes: string,
      public dateCreation: Date,

      public averageRating: number, // Ajoutez cet attribut si ce n'est pas déjà fait
    ) { }
  }
  
  