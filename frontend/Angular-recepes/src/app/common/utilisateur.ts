export class Utilisateur {
    id?: number; // Notez le '?' pour indiquer que 'id' est optionnel
    email: string;
    mot_de_passe: string;
    nom: string;
    prenom: string;
    bio: string;
  
    constructor(
      email: string,
      mot_de_passe: string,
      nom: string,
      prenom: string,
      bio: string,
      id?: number // 'id' est optionnel ici aussi
    ) {
      this.id = id;
      this.email = email;
      this.mot_de_passe = mot_de_passe;
      this.nom = nom;
      this.prenom = prenom;
      this.bio = bio;
    }
  }
  