package abstraction.equipe2;

import abstraction.fourni.Acteur;
import abstraction.fourni.Indicateur;
import abstraction.commun.*;

public class Achat {

	private double cacaoachete;
	private Indicateur historiqueachats;
	
	public double getCacaoachete(){
		return cacaoachete;
	}
	
	
	public void setCacaoAchete(Nestle nestle, IProducteur p) {
		this.cacaoachete = Math.min(p.annonceQuantiteMiseEnVente(nestle), nestle.annonceQuantiteDemandee(p));
		System.out.println("jjjj "+p.toString()+"---"+p.annonceQuantiteMiseEnVente(nestle));
		System.out.println("patate");
		System.out.println(nestle.annonceQuantiteDemandee(p));
	}
	
	
	public Indicateur getHistoriqueachats() {
		return this.historiqueachats;
	}
	
	public Achat(Acteur acteur) {
		this.cacaoachete = 0.0;
		this.historiqueachats = new Indicateur(acteur.getNom(), acteur, this.cacaoachete);
	}
	
	public Achat(double quantite) {
		this.cacaoachete = quantite;
	}//
	
	public void MiseAJourHistorique(Nestle nestle, int etape) {
		this.historiqueachats.getHistorique().ajouter(nestle, etape, this.cacaoachete);
	}
}