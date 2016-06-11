package abstraction.equipe2;

import abstraction.fourni.Historique;
import abstraction.fourni.Acteur;
import abstraction.fourni.Indicateur;

public class Banque_old {
	
	private double banque;
	private Indicateur tresorerie;
		
	
	public Banque_old() {
	this.banque=Constante.TRESORERIE_INITIALE;
	}

	public Banque_old(Nestle_old nestle) {
	this.banque=Constante.TRESORERIE_INITIALE;
	this.tresorerie = new Indicateur("Solde de Nestle", nestle, Constante.TRESORERIE_INITIALE);
	}
	
	public void MiseAJourHistorique(Nestle_old nestle, int etape) {
		this.tresorerie.getHistorique().ajouter(nestle, etape, this.getBanque());
	}
	
	public double getBanque() {
		return banque;
	}
	
	public Indicateur getTresorerie() {
		return tresorerie;
	}

	public double CoutsFixes() {
		return Constante.CHARGES_FIXES;
	}
	
	public void ajouter(double quantite) {
		this.banque+=quantite;
	}
	
	
	


	public void retirer(double quantite) {
		this.banque-=quantite;
	}
}
