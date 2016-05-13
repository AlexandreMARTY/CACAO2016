package abstraction.equipe5;

import java.util.List;

import abstraction.commun.CommandeDistri;
import abstraction.commun.Constantes;
import abstraction.commun.IProducteur;
import abstraction.commun.MondeV1;

public class AchatProd {
	private HistoriqueCommandeProduc hist;
	private Lindt lindt;

	public AchatProd(HistoriqueCommandeProduc hist, Lindt lindt){
		this.hist = hist;
		this.lindt = lindt;
	}
	
	
	// Cr�ation d'une fonction qui calcule la quantit� demand�e en comparant les 2prods
	public double calculQuantiteDemandee(List<CommandeDistri> listeCommandesDist){
		double quantiteTotale=0;
		for (CommandeDistri c : listeCommandesDist){
			quantiteTotale += c.getQuantite();
		}
		double quantiteEnVente = 0;
		for (IProducteur p: lindt.getProducteurs()){
			quantiteEnVente += p.annonceQuantiteMiseEnVente(lindt);
		}
		if (quantiteTotale <= quantiteTotale)
		
		return 0.0;
	}
	
	/**
	 * Indique la quantite demandee au producteur p.
	 */
	public double annonceQuantiteDemandee(IProducteur p,double annoncePrix, double annonceQuantiteMiseEnVente){ 
	 // le reste du monde est pris en compte manuellement dans le next
		double quantiteTotale = Constante.RATIO_CACAO_CHOCOLAT*hist.valeur(Constante.STEP_COURANT);
		if (p==MondeV1.LE_MONDE.getActeur(Constantes.NOM_PRODUCTEUR_1)) {
			return (Math.min(0.3*quantiteTotale, annonceQuantiteMiseEnVente));
		}
		else {
			return (Math.min(0.3*quantiteTotale, annonceQuantiteMiseEnVente));
		}
		
	}
}