package abstraction.equipe2;

import abstraction.fourni.Historique;

public class Stock {
	private double stock;
	private Historique stockprecedents;
	
public static final double COUT_STOCK = 18;
	
	
	public double getStock() {
		return stock;
	}
	
	public Stock () {
		this.stock = 0.0;
		this.stockprecedents = new Historique();
	}
	
	public void AjouterStockAchat(Achat achat) {
		this.stock = this.stock + achat.getDernierecommandeachetee().getCommandesProd();
	}
	
	public void AjouterStockProduction(Production production){
		this.stock = this.stock + production.getProduction();
	}
	
	public void RetirerStockVentes(Vente ventes) {
		this.stock -= ventes.getDernierecommandevendue().getCommandeDis();
	}
	public Historique getStockprecedents() {
		return stockprecedents;
	}
	
	public void MiseAJourHistorique(int etape) {
		this.stockprecedents.ajouter(Nestle, etape, this.stock);
	}
	//Ne conna�t pas encore Nestl�

}
