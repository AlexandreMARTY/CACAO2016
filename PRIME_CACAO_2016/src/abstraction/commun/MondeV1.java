package abstraction.commun;

import abstraction.fourni.Monde;
import abstraction.commun.Constantes;
import abstraction.equipe2.*;

import abstraction.equipe6.Carrefour;

public class MondeV1 extends Monde {
	
	public void peupler() {
		// Il faut créer les acteurs et les ajouter au monde ici.
		// Distributeurs
		Carrefour Ca = new Carrefour("Carrefour", this, 15, 20, 50000);
		
		// Transformateurs
		Transformateur2 t1 = new Transformateur2(Constantes.NOM_TRANSFORMATEUR_1, this);
		
		this.ajouterActeur(t1);
		
		// Producteurs
<<<<<<< HEAD
		
		
=======

		Producteur p1 = new Producteur(Constantes.NOM_PRODUCTEUR_1, 1000.0, 0.0, Monde.LE_MONDE);
		this.ajouterActeur(p1);
>>>>>>> branch 'master' of https://github.com/lakac/CACAO2016.git
	}
}
